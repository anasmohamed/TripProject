package com.one.direction.nabehha.data.network;

import com.one.direction.nabehha.AppExecutors;
import com.one.direction.nabehha.UserPreferencesHelper;
import com.one.direction.nabehha.data.database.dao.TripDao;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.data.network.tripapi.TripAPIService;
import com.one.direction.nabehha.data.network.userapi.UserAPIService;

import java.util.ArrayList;

import retrofit2.Callback;

public class TripRepository {

    private static final Object LOCK = new Object();
    private static TripRepository tripRepositoryInstance;
    private final AppExecutors mExecutors;
    private TripAPIService tripAPIService;
    private TripDao tripDao;



    public TripRepository(TripAPIService tripAPIService,TripDao tripDao, AppExecutors executors) {
        this.tripAPIService = tripAPIService;
        this.tripDao=tripDao;
        this.mExecutors = executors;
    }

    public synchronized static TripRepository getInstance(
            TripAPIService tripAPIService,TripDao tripDao, AppExecutors executors) {
        if (tripRepositoryInstance == null) {
            synchronized (LOCK) {
                tripRepositoryInstance = new TripRepository(tripAPIService,tripDao,
                        executors);
            }
        }
        return tripRepositoryInstance;
    }


    public void insertTripIntoWebService(String tripName, String startPoint, String endPoint,String date,String time,String type,String tripImage,Long userId,String status, Callback<Trip> mTripCallback) {
        tripAPIService.insertTripIntoWebService(new Trip(tripName,startPoint,endPoint,date,time,type,tripImage,userId ,status )).enqueue(mTripCallback);
    }


    public void saveTripImage(Long tripId, byte[] imageByte) {

    }

    public ArrayList<String> getNotes(Long tripId) {
//        return tripDao.getNotes(tripId);
        return null;
    }
}
