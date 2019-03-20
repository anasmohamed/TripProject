package com.one.direction.nabehha.ui.addtrip;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.Reminder;
import com.one.direction.nabehha.databinding.AddTripFragmentBinding;

import java.util.Calendar;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;


public class AddTripFragment extends Fragment {

    AddTripFragmentBinding mAddTripFragmentBinding;
    private AddTripViewModel mViewModel;
    private int mYear, mMonth, mDay, mHour, mMinute;
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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AddTripModelFactory factory = InjectionUtils.provideAddTripViewModelFactory(getContext());

        mViewModel = ViewModelProviders.of(this, factory).get(AddTripViewModel.class);
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

                                mAddTripFragmentBinding.tripTimeEt.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });
        // TODO: Use the ViewModel
        mAddTripFragmentBinding.addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doWork();

                //  mViewModel.AddTripToWebService("anas", "moahmed", "kamal", "anas", "mohamed", "kamal", "ahmed", 1L, "adadsf");

            }
        });

    }

    void doWork() {

        mWorkManager.enqueue(OneTimeWorkRequest.from(Reminder.class));
        getActivity().finish();
    }
}
