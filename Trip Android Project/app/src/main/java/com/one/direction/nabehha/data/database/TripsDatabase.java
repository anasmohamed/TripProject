/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.one.direction.nabehha.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.one.direction.nabehha.data.database.dao.TripDao;
import com.one.direction.nabehha.data.database.model.Trip;


// List of the entry classes and associated TypeConverters
@Database(entities = {Trip.class}, version = 1,exportSchema = false)
public abstract class TripsDatabase extends RoomDatabase {

    private static final String LOG_TAG = TripsDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "trip";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static TripsDatabase sInstance;

    public static TripsDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TripsDatabase.class, TripsDatabase.DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract TripDao tripDao();
}
