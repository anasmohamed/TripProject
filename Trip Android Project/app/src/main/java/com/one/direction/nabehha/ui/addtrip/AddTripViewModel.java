package com.one.direction.nabehha.ui.addtrip;

import android.arch.lifecycle.ViewModel;

import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.network.TripRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTripViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final TripRepository mTripRepository;

    public AddTripViewModel(TripRepository tripRepository) {
        this.mTripRepository = tripRepository;
    }

    public void AddTripToWebService(String tripName, String startPoint, String endPoint, String date, String time, String type, String tripImage, String status) {
        mTripRepository.insertTripIntoWebService(tripName, startPoint, endPoint, date, time, type, tripImage, status, new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {

            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {

            }
        });

    }
}

