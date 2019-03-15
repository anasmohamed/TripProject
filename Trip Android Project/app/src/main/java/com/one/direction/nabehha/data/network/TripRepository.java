package com.one.direction.nabehha.data.network;

import com.one.direction.nabehha.AppExecutors;
import com.one.direction.nabehha.UserPreferencesHelper;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.data.network.tripapi.TripAPIService;
import com.one.direction.nabehha.data.network.userapi.UserAPIService;

import retrofit2.Callback;

public class TripRepository {

    private static final Object LOCK = new Object();
    private static TripRepository tripRepositoryInstance;
    private final AppExecutors mExecutors;
    private TripAPIService tripAPIService;


    public TripRepository(TripAPIService tripAPIService, AppExecutors executors) {
        this.tripAPIService = tripAPIService;
        this.mExecutors = executors;
    }

    public synchronized static TripRepository getInstance(
            TripAPIService tripAPIService, AppExecutors executors) {
        if (tripRepositoryInstance == null) {
            synchronized (LOCK) {
                tripRepositoryInstance = new TripRepository(tripAPIService,
                        executors);
            }
        }
        return tripRepositoryInstance;
    }


    public void insertTripIntoWebService(String tripName, String startPoint, String endPoint,String date,String time,String type,String tripImage,String status, Callback<Trip> mTripCallback) {
        tripAPIService.insertTripIntoWebService(new Trip(tripName,startPoint,endPoint,date,tripImage,time ,status ,type)).enqueue(mTripCallback);
    }



}
