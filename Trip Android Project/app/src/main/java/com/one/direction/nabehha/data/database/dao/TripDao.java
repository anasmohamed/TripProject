package com.one.direction.nabehha.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.one.direction.nabehha.data.database.model.Trip;

import java.util.List;

@Dao
public interface TripDao {
    @Insert
    void insert(Trip trip);

    @Query("DELETE FROM trip where id = id")
    void deleteTrip(Long id);

    @Query("DELETE FROM trip")
    void deleteAll();
    @Query("SELECT * from trip where status = 'upcmming'")
    LiveData<List<Trip>> getUpcomingTrips();

    @Query("SELECT * from trip where status = 'done'")
    LiveData<List<Trip>> getPastTrips();

}
