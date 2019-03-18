package com.one.direction.nabehha;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Reminder extends Worker {
    FragmentActivity context;

    public Reminder(@NonNull FragmentActivity context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;

    }

    @NonNull
    @Override
    public Result doWork() {
        try {

            ReminderDialogFragment reminderDialogFragment = new ReminderDialogFragment();
            reminderDialogFragment.show(context.getSupportFragmentManager(), "Trip Reminder");
            return Result.success();

        } catch (Throwable throwable) {
            return Result.failure();
        }
    }
}