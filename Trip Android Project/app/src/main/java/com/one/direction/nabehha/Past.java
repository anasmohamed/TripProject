package com.one.direction.nabehha;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.direction.nabehha.databinding.PastBinding;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class Past extends Fragment {

PastBinding mPastBinding;
    public Past() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPastBinding = DataBindingUtil.inflate(inflater, R.layout.past, container, false);
        //TODO  implement viewmodel
        Picasso.get()
                .load(new URL(Utilities.getGoogleMapImageForTrips(mPastViewModel.getTripList())))
                .placeholder(R.drawable.ic_close_black_24dp)
                .error(R.drawable.ic_close_white_24dp)
                .into(mPastBinding.allTripGoogleImageImg);

        return mPastBinding.getRoot();
    }


}
