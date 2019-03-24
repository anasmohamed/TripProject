package com.one.direction.nabehha;


import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.service.TripAlarmDialog;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Reminder extends Worker {
    Trip trip;
    Context context;
    MediaPlayer mp;


    public Reminder(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        this.context = context;

    }

    @NonNull
    @Override
    public Result doWork() {
        trip = new Trip();
        trip = deserializeFromJson(getInputData().getString("trip"));
        TripAlarmDialog.startTripAlarm(context,trip);
        mp = MediaPlayer.create(context, R.raw.alarm);
        mp.start();

        return Result.success();

    }

    public Trip deserializeFromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Trip.class);
    }

}