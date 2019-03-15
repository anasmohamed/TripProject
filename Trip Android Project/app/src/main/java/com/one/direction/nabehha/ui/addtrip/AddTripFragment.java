package com.one.direction.nabehha.ui.addtrip;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.databinding.AddTripFragmentBinding;


public class AddTripFragment extends Fragment {

    AddTripFragmentBinding binder;
    private AddTripViewModel mViewModel;

    public static AddTripFragment newInstance() {
        return new AddTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binder = DataBindingUtil.inflate(inflater, R.layout.add_trip_fragment, container, false);
        View view = binder.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AddTripModelFactory factory = InjectionUtils.provideAddTripViewModelFactory(getContext());

        mViewModel = ViewModelProviders.of(this,factory).get(AddTripViewModel.class);

        // TODO: Use the ViewModel
        binder.addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.AddTripToWebService("anas", "moahmed", "kamal", "anas", "mohamed", "kamal", "ahmed",  1,"adadsf");

            }
        });
    }

}
