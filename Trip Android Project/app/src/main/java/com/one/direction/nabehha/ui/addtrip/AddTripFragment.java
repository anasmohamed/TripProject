package com.one.direction.nabehha.ui.addtrip;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;
import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.Reminder;
import com.one.direction.nabehha.ReturnResult;
import com.one.direction.nabehha.Utilities;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.databinding.AddTripFragmentBinding;
import com.one.direction.nabehha.service.DownloadImage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;


public class AddTripFragment extends Fragment {

    private static final String TAG = "Add trip Fragment:";
    AddTripFragmentBinding mAddTripFragmentBinding;
    Fragment fragment = new AddNoteFragment();
    Bundle bundle;
    Calendar calendar;
    ArrayList<String> notesArrayList;
    NotesAdapter notesAdapter;
    Calendar calendarTime;
    int datePickerYear, datePickerMonth, datePickerDay, timePickerHour, timePickerMinute;
    private AddTripViewModel mViewModel;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String mTripName, mTripStartPoint, mTripEndPoint, mTripDate, mTripTime, mTripStatus, mTripType;
    private double startPointLatitude;
    private double startPointLongitude;
    private double endPointLatitude;
    private double endPointLongitude;
    private ArrayList<String> mTripNotes;
    private WorkManager mWorkManager;


    public static AddTripFragment newInstance() {
        return new AddTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mAddTripFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.add_trip_fragment, container, false);
        View view = mAddTripFragmentBinding.getRoot();

        mWorkManager = WorkManager.getInstance();
        fragment = new AddNoteFragment();
        mTripNotes = new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.select_state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAddTripFragmentBinding.addTripTypeSpinner.setAdapter(adapter);
        mAddTripFragmentBinding.addTripTypeSpinner.setSelection(adapter.getPosition("Rounded"));
        notesArrayList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesArrayList, getActivity());
        mAddTripFragmentBinding.listViewNotes.setAdapter(notesAdapter);

        mAddTripFragmentBinding.cancelTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AddTripModelFactory factory = InjectionUtils.provideAddTripViewModelFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(AddTripViewModel.class);

        mAddTripFragmentBinding.imageAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mAddTripFragmentBinding.textViewAddNote.getText().toString().isEmpty()) {

                    notesArrayList.add(mAddTripFragmentBinding.textViewAddNote.getText().toString());
                    mAddTripFragmentBinding.textViewAddNote.setText("");
                    notesAdapter.notifyDataSetChanged();
                }
            }
        });

        mAddTripFragmentBinding.tripTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                mAddTripFragmentBinding.tripTimeET.setText(hourOfDay + ":" + minute);
                                timePickerHour = hourOfDay;
                                timePickerMinute = minute;

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });
        mAddTripFragmentBinding.tripDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar= Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mAddTripFragmentBinding.tripDateET.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                datePickerMonth = monthOfYear;
                                datePickerDay = dayOfMonth;
                                datePickerYear = year;
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }

        });
        // 2 fragment

        PlaceAutocompleteFragment startPointFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.start_point_autocomplete_fragment);
        if (startPointFragment != null) {
            startPointFragment.setHint("Start Point");
            startPointFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    mTripStartPoint = String.valueOf(place.getName());
                    startPointLatitude = place.getLatLng().latitude;
                    startPointLongitude = place.getLatLng().longitude;
                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    Log.e(TAG, "An error occurred: " + status);
                }
            });
        }
        PlaceAutocompleteFragment endPointFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.end_point_autocomplete_fragment);
        if (endPointFragment != null) {
            endPointFragment.setHint("Destination");
            endPointFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    mTripEndPoint = String.valueOf(place.getName());
                    endPointLatitude = place.getLatLng().latitude;
                    endPointLongitude = place.getLatLng().longitude;


                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    Log.e(TAG, "An error occurred: " + status);
                }
            });


            // TODO: Use the ViewModel
            mAddTripFragmentBinding.addTripBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTripName = mAddTripFragmentBinding.tripNameET.getText().toString();
                    mTripTime = mAddTripFragmentBinding.tripTimeET.getText().toString();
                    mTripType = mAddTripFragmentBinding.addTripTypeSpinner.getSelectedItem().toString();
                    mTripDate = mAddTripFragmentBinding.tripDateET.getText().toString();
                    if (!mTripDate.isEmpty() &&
                            !mTripName.isEmpty() &&
                            !mTripName.isEmpty() &&
                            !mTripStartPoint.isEmpty() && !mTripEndPoint.isEmpty()
                            && !String.valueOf(endPointLatitude).isEmpty()
                            && !String.valueOf(endPointLongitude).isEmpty() &&
                            !String.valueOf(startPointLatitude).isEmpty()
                            && !String.valueOf(startPointLongitude).isEmpty()
                    ) {
                        calendarTime = Calendar.getInstance();
                        final Trip trip = new Trip();
                        trip.setTripName(mTripName);
                        trip.setType(mTripType);
                        trip.setStatus(mTripStatus);
                        trip.setTime(mTripTime);
                        trip.setDate(mTripDate);
                        trip.setStartPointLatitude(startPointLatitude);
                        trip.setStartPointLongitude(startPointLongitude);
                        trip.setEndPointLatitude(endPointLatitude);
                        trip.setEndPointLongitude(endPointLongitude);
                        trip.setEndPointAddress(mTripEndPoint);
                        trip.setStartPointAddress(mTripStartPoint);
                        trip.setNotes(notesArrayList);
                        calendarTime.set(datePickerYear, datePickerMonth, datePickerDay, timePickerHour, timePickerMinute);

                        mViewModel.AddTripToWebService(trip, new ReturnResult(){
                            @Override
                            public void onReturn(Object o) {
                                trip.setTripId((String) o);
                                tripReminder(trip);
                                getActivity().finish();
                            }
                        });
                        mViewModel.addTripToDatabase(mTripName, "a", "b", mTripDate, mTripTime, mTripType, null, 1L, mTripStatus, getContext());

                    } else {
                        Toast.makeText(getActivity(), "Please Fill All Required All Fields", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }


    void tripReminder(Trip trip) {
        Data.Builder builder = new Data.Builder();
         @SuppressLint("RestrictedApi") Data tripData = builder.put("trip", serializeToJson(trip)).build();
        Log.i("final time", getTimeInSeconds() + "");
        OneTimeWorkRequest myWork =
                new OneTimeWorkRequest.Builder(Reminder.class).setInputData(tripData).addTag(trip.getTripId())
                        .setInitialDelay(getTimeInSeconds(), TimeUnit.SECONDS).// Use this when you want to add initial delay or schedule initial work to `OneTimeWorkRequest` e.g. setInitialDelay(2, TimeUnit.HOURS)
                        build();
        mWorkManager.enqueue(myWork);
        getActivity().finish();
    }

    long getTimeInSeconds() {

        long diffInMs = calendarTime.getTime().getTime() - calendar.getTime().getTime();
        return TimeUnit.MILLISECONDS.toSeconds(diffInMs);
    }

    public String serializeToJson(Trip trip) {
        Gson gson = new Gson();
        return gson.toJson(trip);
    }


}