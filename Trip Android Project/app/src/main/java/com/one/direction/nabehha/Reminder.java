package com.one.direction.nabehha;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;

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