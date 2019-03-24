package com.one.direction.nabehha.ui.addtrip;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.one.direction.nabehha.AppConstants;
import com.one.direction.nabehha.Utilities;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.network.TripRepository;
import com.one.direction.nabehha.service.DownloadImage;

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

    public void AddTripToWebService(final Trip trip,
                                    Context mContext ) {
        mDatabaseReference = mFirebaseDatabase.getReference("Trips");
        String tripId=mDatabaseReference.push().getKey();
        DownloadImage.startDownloadAndSaveInDb(mContext, Utilities.getGoogleMapImageForTrip(trip), tripId);
        mDatabaseReference = mFirebaseDatabase.getReference("Trips/" + AppConstants.CURRENT_USER_ID + "/scheduled/"+tripId);
        final HashMap<String, Object> nameKey = new HashMap<String, Object>() {{
            put("tripName", trip.getTripName());
            put("startPointAddress", trip.getStartPointAddress());
            put("endPointAddress", trip.getEndPointAddress());
            put("startPointLatitude", trip.getStartPointLatitude());
            put("startPointLongitude", trip.getStartPointLongitude());
            put("endPointLatitude", trip.getEndPointLatitude());
            put("endPointLongitude", trip.getEndPointLongitude());
            put("date", trip.getDate());
            put("time", trip.getTime());
            put("type", trip.getType());
            put("notes", trip.getNotes());
        }};
        mDatabaseReference.setValue(nameKey);

    }

}

