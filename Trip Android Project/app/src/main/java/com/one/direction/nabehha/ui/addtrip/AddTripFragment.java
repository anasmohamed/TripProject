package com.one.direction.nabehha.ui.addtrip;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.Reminder;
import com.one.direction.nabehha.databinding.AddTripFragmentBinding;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;


public class AddTripFragment extends Fragment {

    AddTripFragmentBinding mAddTripFragmentBinding;
    AutocompleteSupportFragment startPointPlaceFragment, endPointPlaceFragment;
    Fragment fragment = new AddNoteFragment();
    Bundle bundle;
    private AddTripViewModel mViewModel;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String mTripName, mTripStartPoint, mTripEndPoint, mTripDate, mTripTime, mTripStatus, mTripType;
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
        startPointPlaceFragment = (AutocompleteSupportFragment)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.start_point_autocomplete_fragment);
        endPointPlaceFragment = (AutocompleteSupportFragment)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.end_point_autocomplete_fragment);
        fragment = new AddNoteFragment();
        mTripNotes = new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.select_state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAddTripFragmentBinding.addTripTypeSpinner.setAdapter(adapter);
        mAddTripFragmentBinding.addTripTypeSpinner.setSelection(adapter.getPosition("Rounded"));

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AddTripModelFactory factory = InjectionUtils.provideAddTripViewModelFactory(getContext());

        mViewModel = ViewModelProviders.of(this, factory).get(AddTripViewModel.class);

        mTripName = mAddTripFragmentBinding.tripNameET.getText().toString();
        bundle = this.getArguments();
        if (bundle != null) {
            mTripNotes = bundle.getStringArrayList("notes");
            Log.e("tripNotes", mTripNotes.size() + "");

        }
//        startPointPlaceFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                mTripStartPoint = place.getName();
//
//            }

//            @Override
//            public void onError(@NonNull Status status) {
//
//            }
//        });

//        endPointPlaceFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                mTripEndPoint = place.getName();
//
//            }
//
//            @Override
//            public void onError(@NonNull Status status) {
//
//            }
//        });

        mAddTripFragmentBinding.addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack("noteFragment");
                fragmentTransaction.commit();


            }
        });

        mAddTripFragmentBinding.tripTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mAddTripFragmentBinding.tripDateET.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        mAddTripFragmentBinding.tripDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                mAddTripFragmentBinding.tripTimeET.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });
        mTripDate = mAddTripFragmentBinding.tripDateET.getText().toString();
        mTripTime = mAddTripFragmentBinding.tripTimeET.getText().toString();
        mTripType = mAddTripFragmentBinding.addTripTypeSpinner.getSelectedItem().toString();


        // TODO: Use the ViewModel
        mAddTripFragmentBinding.addTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // doWork();
                //   mViewModel.AddTripToWebService(mTripName, "startpoint", "startpoint", mTripDate, mTripTime, mTripType, "", 1L, mTripStatus);

                mViewModel.addTripToDatabase(mTripName, "a", "b", mTripDate, mTripTime, mTripType, null, 1L, mTripStatus, getContext());
            }
        });

    }


    void doWork() {

        mWorkManager.enqueue(OneTimeWorkRequest.from(Reminder.class));
    }
}