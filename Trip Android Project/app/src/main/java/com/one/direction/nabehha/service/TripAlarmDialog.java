package com.one.direction.nabehha.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.one.direction.nabehha.R;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.databinding.ReminderDialogFragmentBinding;
import com.one.direction.nabehha.service.note.FloatingWidgetService;

import androidx.work.WorkManager;

import static com.one.direction.nabehha.AppConstants.PARCELABLE_TRIP;

public class TripAlarmDialog extends Service {
    Trip trip;
    LayoutInflater inflater;
    private WindowManager mWindowManager;
    private ReminderDialogFragmentBinding mDialogBinder;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            if (intent.getParcelableExtra(PARCELABLE_TRIP) != null)
                trip = intent.getParcelableExtra(PARCELABLE_TRIP);
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TripAlarmDialog.class.getName(),"Started .............");
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        //Init LayoutInflater
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        addFloatingWidgetView(inflater);
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
//        mDialogBinder.tripNameAlertTV.setText(trip.getType());
        mDialogBinder.startReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTrip(trip);
                endDialog();
            }
        });
        mDialogBinder.laterReminderBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                ProcrastinateTheTrip(trip);
                endDialog();
            }
        });
        mDialogBinder.cancelRemiderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTrip(trip);
                endDialog();
            }
        });
    }

    private void endDialog() {
        mWindowManager.removeViewImmediate(mDialogBinder.getRoot());
        stopSelf();
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
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

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
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(trip.getTripName())
                        .setContentText("my time is now ")
                        .setOngoing(true);
        //

        Intent intent = new Intent(this, TripAlarmDialog.class);
        intent.putExtra(PARCELABLE_TRIP,trip);
        PendingIntent pi = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mBuilder.setAutoCancel(true);
        notificationManager.notify(0, mBuilder.build());


    }

    private void startTrip(Trip trip) {
//        InjectionUtils.provideTripRepository(context).changeTripStatus(trip.getId(),"past");
        //TODO remove alarm for this trip

        WorkManager.getInstance().cancelAllWorkByTag(trip.getType());//TODO Cancel with id better i think
        startFloatingWidgetService(trip.getTripId());
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + trip.getEndPointLatitude()+","+trip.getEndPointLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    private void startFloatingWidgetService(String tripId) {
        Intent intent = new Intent(this, FloatingWidgetService.class);
        intent.putExtra(DownloadImage.TRIP_ID, tripId);
        startService(intent);

    }

    static public void startTripAlarm(Context mContext, Trip trip) {
        Intent intent = new Intent(mContext, TripAlarmDialog.class);
        intent.putExtra(PARCELABLE_TRIP, trip);
        mContext.startService(intent);
    }

}
