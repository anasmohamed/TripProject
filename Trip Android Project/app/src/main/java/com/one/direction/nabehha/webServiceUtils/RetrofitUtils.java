package com.one.direction.nabehha.webServiceUtils;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.one.direction.nabehha.data.database.model.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static final String BASE_URL = "http://10.0.2.2:8084/TripWebService/tripservice/trip/";

    private Retrofit retrofit;
    private TripServiceApi tripServiceApi;
    FirebaseDatabase mFirebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference;
    private List<Trip> trips;

    public RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tripServiceApi = retrofit.create(TripServiceApi.class);
    }

    public void getTripsUsingRetrofit(String userId, final String tripStatus, ValueEventListener valueEventListener) {
        mDatabaseReference = mFirebaseDatabase.getReference("Trips/" + userId + "/"+tripStatus);
        mDatabaseReference.addValueEventListener(valueEventListener);
    }

    public boolean deleteTripsUsingRetrofit(String userId,String tripStatus,String tripId) {
       return  mFirebaseDatabase.getReference("Trips/" + userId + "/"+tripStatus+"/"+tripId).removeValue().isSuccessful();
    }

}
