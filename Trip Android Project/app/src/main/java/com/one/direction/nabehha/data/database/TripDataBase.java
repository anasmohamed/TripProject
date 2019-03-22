package com.one.direction.nabehha.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.one.direction.nabehha.data.database.dao.NoteDao;
import com.one.direction.nabehha.data.database.dao.TripDao;
import com.one.direction.nabehha.data.database.model.Trip;

@Database(entities = {Trip.class}, version = 1, exportSchema = false)
public abstract  class TripDataBase extends RoomDatabase {
    public abstract TripDao tripDao();
  //  public abstract NoteDao noteDao();

    private static volatile TripDataBase INSTANCE;

  public   static TripDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TripDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripDataBase.class, "trip_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
