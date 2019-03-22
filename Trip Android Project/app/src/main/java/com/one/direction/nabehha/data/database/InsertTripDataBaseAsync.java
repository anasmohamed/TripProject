package com.one.direction.nabehha.data.database;

import android.os.AsyncTask;

import com.one.direction.nabehha.data.database.dao.TripDao;
import com.one.direction.nabehha.data.database.model.Trip;

public class InsertTripDataBaseAsync extends AsyncTask<Trip, Void, Void> {
    private TripDao asyncTaskDao;

    InsertTripDataBaseAsync(TripDao dao) {
        asyncTaskDao = dao;
    }


    @Override
    protected Void doInBackground(final Trip... trips) {
        asyncTaskDao.insertTrip(trips[0]);
        return null;
    }
}
