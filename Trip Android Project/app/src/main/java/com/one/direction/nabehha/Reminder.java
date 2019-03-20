package com.one.direction.nabehha;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.databinding.ReminderDialogFragmentBinding;
import com.one.direction.nabehha.service.DownloadImage;
import com.one.direction.nabehha.service.note.FloatingWidgetService;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

public class Reminder extends Worker {
    Context context;
    MediaPlayer mp;
    private WindowManager mWindowManager;
    private Point szWindow = new Point();
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);

            getWindowManagerDefaultDisplay();

            //Init LayoutInflater
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            addFloatingWidgetView(inflater);
        }
    };
    private ReminderDialogFragmentBinding mDialogBinder;

    public Reminder(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        this.context = context;

    }


    @NonNull
    @Override
    public Result doWork() {
        mHandler.sendEmptyMessage(0);

            mp = MediaPlayer.create(context, R.raw.alarm);
            mp.start();
            return Result.success();


    }

    private void getWindowManagerDefaultDisplay() {
        mWindowManager.getDefaultDisplay().getSize(szWindow);
    }






    private void addFloatingWidgetView(LayoutInflater inflater) {
        //Inflate the floating view layout we created
//         mDialog = inflater.inflate(R.layout.reminder_dialog_fragment, null);
        mDialogBinder=DataBindingUtil.inflate(inflater, R.layout.reminder_dialog_fragment, null, false);
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

        //Initially view will be added to top-left corner, you change x-y coordinates according to your need


        //Add the view to the window
        mWindowManager.addView(mDialogBinder.getRoot(), params);
        mDialogBinder.startRemiderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startTrip();
                mWindowManager.removeViewImmediate(mDialogBinder.getRoot());
            }
        });
        mDialogBinder.laterRemiderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcrastinateTheTrip();
            }
        });
        mDialogBinder.cancelRemiderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTrip();
            }
        });
    }

    private void cancelTrip() {

    }

    private void ProcrastinateTheTrip() {

    }

    private void startTrip(Trip trip) {
//        InjectionUtils.provideTripRepository(context).changeTripStatus(trip.getId(),"past");
        //TODO remove alarm for this trip

        startFloatingWidgetService(trip.getId());
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+trip.getEndPoint());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);

    }


    private void startFloatingWidgetService(Long tripId) {
        Intent intent=new Intent(context, FloatingWidgetService.class);
        intent.putExtra(DownloadImage.TRIP_ID,tripId);
        context.startService(intent);

    }

}