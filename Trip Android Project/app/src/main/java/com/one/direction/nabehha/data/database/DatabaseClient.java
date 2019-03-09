package com.one.direction.nabehha.data.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {
    private Context  mContext;
    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;
    private DatabaseClient(Context mContext) {
        this.mContext = mContext;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "Trip").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
