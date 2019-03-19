package com.one.direction.nabehha;


import android.support.annotation.NonNull;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Reminder extends Worker {

    Context context;
    MediaPlayer mp;

    public Reminder(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        this.context = context;

    }


    @NonNull
    @Override
    public Result doWork() {

            ReminderDialogFragment reminderDialogFragment = new ReminderDialogFragment(context);
            reminderDialogFragment.show();
            mp = MediaPlayer.create(context, R.raw.alarm);
            mp.start();
            return Result.success();


    }
}