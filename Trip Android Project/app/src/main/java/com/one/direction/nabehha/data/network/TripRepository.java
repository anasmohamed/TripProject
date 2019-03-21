package com.one.direction.nabehha.data.network;

import android.content.Context;
import android.os.AsyncTask;

import com.one.direction.nabehha.AppExecutors;
import com.one.direction.nabehha.data.database.TripDataBase;
import com.one.direction.nabehha.UserPreferencesHelper;
import com.one.direction.nabehha.data.database.dao.TripDao;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.network.tripapi.TripAPIService;

import java.util.ArrayList;

import retrofit2.Callback;

public class TripRepository {

    private static final Object LOCK = new Object();
    private static TripRepository tripRepositoryInstance;
    private final AppExecutors mExecutors;
    private TripAPIService tripAPIService;
    private TripDataBase tripDataBase;


    public TripRepository(TripAPIService tripAPIService,TripDataBase tripDataBase,AppExecutors executors) {
        this.tripAPIService = tripAPIService;
        this.mExecutors = executors;
       this.tripDataBase = tripDataBase;
    }

    public synchronized static TripRepository getInstance(
            TripAPIService tripAPIService,TripDataBase tripDataBase, AppExecutors executors) {
        if (tripRepositoryInstance == null) {
            synchronized (LOCK) {
                tripRepositoryInstance = new TripRepository(tripAPIService,tripDataBase,
                        executors);
            }
        }
        return tripRepositoryInstance;
    }

    public void insertTripIntoDatabase(String tripName, String startPoint, String endPoint, String date, String time, String type, String tripImage, Long userId, String status, Context context) {
      ;
        final Trip trip = new Trip();
        trip.setTripName(tripName);
        trip.setStartPoint(startPoint);
        trip.setEndPoint(endPoint);
        trip.setDate(date);
        trip.setTime(time);
        trip.setStatus(status);
        trip.setType(type);
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                tripDataBase.tripDao().insertTrip(trip);
            }
        });
       // new PopulateDataBaseAsync(TripDataBase.getDatabase(context)).execute(trip);
    }


    public void insertTripIntoWebService(String tripName, String startPoint, String endPoint, String date, String time, String type, String tripImage, Long userId, String status, Callback<Trip> mTripCallback) {
        //   tripAPIService.insertTripIntoWebService(new Trip(tripName,startPoint,endPoint,date,time,type,tripImage,userId ,status )).enqueue(mTripCallback);
    }

    private static class PopulateDataBaseAsync extends AsyncTask<Trip, Void, Void> {


        private final TripDao mTripDao;
        //  private final NoteDao mNoteDao;
        Trip trip;
        private TripRepository delegate = null;

        PopulateDataBaseAsync(TripDataBase db) {
            mTripDao = db.tripDao();
            //mNoteDao = db.noteDao();
            trip = new Trip();
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mTripDao.insertTrip(trip);
            return null;
        }
    }
    public void saveTripImage(Long tripId, byte[] imageByte) {

    }

    public ArrayList<String> getNotes(Long tripId) {
//        return tripDao.getNotes(tripId);
        return null;
    }
}
