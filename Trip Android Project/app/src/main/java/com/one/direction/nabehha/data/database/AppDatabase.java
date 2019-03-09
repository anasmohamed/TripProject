package com.one.direction.nabehha.data.database;

import android.arch.persistence.room.Database;

import android.arch.persistence.room.RoomDatabase;


import com.one.direction.nabehha.data.database.dao.UserDao;
import com.one.direction.nabehha.data.database.model.User;


@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
public  abstract UserDao userDao();
}
