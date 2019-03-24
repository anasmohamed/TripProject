package com.one.direction.nabehha;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.databinding.ActivityEditTripBinding;
import com.one.direction.nabehha.ui.addtrip.AddTripModelFactory;
import com.one.direction.nabehha.ui.addtrip.AddTripViewModel;
import com.one.direction.nabehha.ui.addtrip.NotesAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class EditTrip extends AppCompatActivity {

    ActivityEditTripBinding activityEditTripBinding;
    Trip trip;
    int datePickerYear, datePickerMonth, datePickerDay, timePickerHour, timePickerMinute;
    Calendar c;
    ArrayList<String> notesArrayList;
    NotesAdapter notesAdapter;
    Calendar calendarTime;
    Trip incomeTrip;
    private AddTripViewModel mViewModel;
    private String mTripName, mTripStartPoint, mTripEndPoint, mTripDate, mTripTime, mTripStatus, mTripType;
    private double startPointLatitude;
    private double startPointLongitude;
    private double endPointLatitude;
    private double endPointLongitude;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private WorkManager mWorkManager;
    public static final String DISPLAY_TRIP_OBJECT = "tripObject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditTripBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_trip);
        mWorkManager = WorkManager.getInstance();
        AddTripModelFactory factory = InjectionUtils.provideAddTripViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this, factory).get(AddTripViewModel.class);
        if (!getIntent().getExtras().isEmpty()) {
            incomeTrip = getIntent().getParcelableExtra(DISPLAY_TRIP_OBJECT);
        }
        activityEditTripBinding.imageAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesArrayList.add(activityEditTripBinding.textViewAddNote.getText().toString());
                activityEditTripBinding.textViewAddNote.setText("");
                notesAdapter.notifyDataSetChanged();

            }
        });

        activityEditTripBinding.tripTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditTrip.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                activityEditTripBinding.tripTimeET.setText(hourOfDay + ":" + minute);
                                timePickerHour = hourOfDay;
                                timePickerMinute = minute;

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });
        activityEditTripBinding.tripDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTrip.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                activityEditTripBinding.tripDateET.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                datePickerMonth = monthOfYear;
                                datePickerDay = dayOfMonth;

                                datePickerYear = year;
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }

        });
        PlaceAutocompleteFragment startPointFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.start_point_autocomplete_fragment);
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
                }
            });
        }
        PlaceAutocompleteFragment endPointFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.end_point_autocomplete_fragment);
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
                }
            });
        }
        activityEditTripBinding.editTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTripName = activityEditTripBinding.tripNameET.getText().toString();
                mTripTime = activityEditTripBinding.tripTimeET.getText().toString();
                mTripType = activityEditTripBinding.addTripTypeSpinner.getSelectedItem().toString();
                mTripDate = activityEditTripBinding.tripDateET.getText().toString();
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
                    Trip trip = new Trip();
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
                    calendarTime.set(datePickerYear, datePickerMonth, datePickerDay, timePickerHour, timePickerMinute);
                    tripReminder(trip);
                    mViewModel.AddTripToWebService(trip, getApplicationContext());
                    mViewModel.addTripToDatabase(mTripName, "a", "b", mTripDate, mTripTime, mTripType, null, 1L, mTripStatus, EditTrip.this);
                    finish();
                } else {
                    Toast.makeText(EditTrip.this, "Please Fill All Required All Fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    void tripReminder(Trip trip) {
        Data.Builder builder = new Data.Builder();
        @SuppressLint("RestrictedApi") Data tripData = builder.put("trip", serializeToJson(trip)).build();
        Log.i("final time", getTimeInSeconds() + "");
        OneTimeWorkRequest myWork =
                new OneTimeWorkRequest.Builder(Reminder.class).setInputData(tripData).addTag(trip.getTripName() + trip.getDate() + trip.getTime())
                        .setInitialDelay(getTimeInSeconds(), TimeUnit.SECONDS).// Use this when you want to add initial delay or schedule initial work to `OneTimeWorkRequest` e.g. setInitialDelay(2, TimeUnit.HOURS)
                        build();
        mWorkManager.enqueue(myWork);
        finish();
    }

    long getTimeInSeconds() {

        long diffInMs = calendarTime.getTime().getTime() - c.getTime().getTime();
        return TimeUnit.MILLISECONDS.toSeconds(diffInMs);
    }

    public String serializeToJson(Trip trip) {
        Gson gson = new Gson();
        return gson.toJson(trip);
    }

}




