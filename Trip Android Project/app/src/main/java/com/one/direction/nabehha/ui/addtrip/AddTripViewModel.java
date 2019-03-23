package com.one.direction.nabehha.ui.addtrip;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.one.direction.nabehha.AppConstants;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.network.TripRepository;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTripViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    FirebaseDatabase mFirebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference;
    private final TripRepository mTripRepository;

    public AddTripViewModel(TripRepository tripRepository) {
        this.mTripRepository = tripRepository;


    }

    public void addTripToDatabase(String tripName, String startPoint, String endPoint,
                                  String date, String time, String type, String tripImage, Long userId, String status, Context context) {
        mTripRepository.insertTripIntoDatabase(tripName, startPoint, endPoint, date, time, type, tripImage, userId, status, context);
    }

    public void AddTripToWebService(final String tripName, final String startPoint, final String endPoint, final double startPointLatitude,
                                    final double startPointLongitude, final double endPointLatitude, final double endPointLongitude, final String date, final String time, final String type) {
//                mTripRepository.insertTripIntoWebService(tripName, startPoint, endPoint, date, time, type, tripImage,userId, status, new Callback<Trip>() {
//            @Override
//            public void onResponse(Call<Trip> call, Response<Trip> response) {
//                Log.e("Add Trip",response.message());
//
//            }
//
//            @Override
//            public void onFailure(Call<Trip> call, Throwable t) {
//                Log.e ("error add trip ",t.getMessage());
//
//
//            }
//        });
        mDatabaseReference = mFirebaseDatabase.getReference("Trips");
        String tripId=mDatabaseReference.push().getKey();
        mDatabaseReference = mFirebaseDatabase.getReference("Trips/" + AppConstants.CURRENT_USER_ID + "/scheduled/"+tripId);
        final HashMap<String, Object> nameKey = new HashMap<String, Object>() {{
            put("tripName", tripName);
            put("startPointAddress", startPoint);
            put("endPointAddress", endPoint);
            put("startPointLatitude", startPointLatitude);
            put("startPointLongitude", startPointLongitude);
            put("endPointLatitude", endPointLatitude);
            put("endPointLongitude", endPointLongitude);
            put("date", date);
            put("time", time);
            put("type", type);
        }};
        mDatabaseReference.setValue(nameKey);

    }

}

