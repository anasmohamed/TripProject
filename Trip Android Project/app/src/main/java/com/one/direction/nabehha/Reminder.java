package com.one.direction.nabehha;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.databinding.ReminderDialogFragmentBinding;
import com.one.direction.nabehha.service.DownloadImage;
import com.one.direction.nabehha.service.note.FloatingWidgetService;

import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

public class Reminder extends Worker {
    Trip trip;
    Context context;
    MediaPlayer mp;
    LayoutInflater inflater;
    private WindowManager mWindowManager;
    private ReminderDialogFragmentBinding mDialogBinder;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);

            //Init LayoutInflater
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            addFloatingWidgetView(inflater);
        }
    };

    public Reminder(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        this.context = context;

    }


    @NonNull
    @Override
    public Result doWork() {
        trip = new Trip();
        trip = deserializeFromJson(getInputData().getString("trip"));
        Log.i("trip Id in work manger", trip.getType() + "");
        mHandler.sendEmptyMessage(0);
        mp = MediaPlayer.create(context, R.raw.alarm);
        mp.start();
        onStopped();
        return Result.success();

    }

    public Trip deserializeFromJson(String jsonString) {
        Gson gson = new Gson();
        Trip myClass = gson.fromJson(jsonString, Trip.class);
        return myClass;
    }


    private void addFloatingWidgetView(LayoutInflater inflater) {
        //Inflate the floating view layout we created
//         mDialog = inflater.inflate(R.layout.reminder_dialog_fragment, null);
        mDialogBinder = DataBindingUtil.inflate(inflater, R.layout.reminder_dialog_fragment, null, false);
        WindowManager.LayoutParams params;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                            | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    PixelFormat.TRANSLUCENT);
            params.gravity = Gravity.CENTER;
        } else {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                            | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    PixelFormat.TRANSLUCENT);
            //Specify the view position
            params.gravity = Gravity.CENTER;
        }

        //TODO get the specific trip and pass to trip

        //Add the view to the window
        mWindowManager.addView(mDialogBinder.getRoot(), params);
        mDialogBinder.tripNameAlertTV.setText(trip.getType());
        mDialogBinder.startReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTrip(trip);
                mWindowManager.removeViewImmediate(mDialogBinder.getRoot());
            }
        });
        mDialogBinder.laterReminderBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                ProcrastinateTheTrip(trip);
                mWindowManager.removeViewImmediate(mDialogBinder.getRoot());

            }
        });
        mDialogBinder.cancelRemiderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTrip(trip);
                mWindowManager.removeViewImmediate(mDialogBinder.getRoot());
            }
        });
    }

    private void cancelTrip(Trip trip) {
        //        InjectionUtils.provideTripRepository(context).changeTripStatus(trip.getId(),"cancel");
        //TODO stop work manger alarm
        WorkManager.getInstance().cancelAllWorkByTag(trip.getType());

    }

    private void ProcrastinateTheTrip(Trip trip) {
        //TODO permenate  notification
        //Stop work manger alarm
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "some_channel_id";
        CharSequence channelName = "Some Channel";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(trip.getTripName())
                        .setContentText("my time is now ")
                        .setOngoing(true);
        //
        addFloatingWidgetView(inflater);
        Intent intent = new Intent(getApplicationContext(), this.getClass());
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        notificationManager.notify(0, mBuilder.build());


    }

    private void startTrip(Trip trip) {
//        InjectionUtils.provideTripRepository(context).changeTripStatus(trip.getId(),"past");
        //TODO remove alarm for this trip
        WorkManager.getInstance().cancelAllWorkByTag(trip.getType());
        startFloatingWidgetService(trip.getTripId());
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + trip.getEndPoint());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);

    }


    private void startFloatingWidgetService(Long tripId) {
        Intent intent = new Intent(context, FloatingWidgetService.class);
        intent.putExtra(DownloadImage.TRIP_ID, tripId);
        context.startService(intent);

    }

    @Override
    public void onStopped() {
        super.onStopped();

    }
}