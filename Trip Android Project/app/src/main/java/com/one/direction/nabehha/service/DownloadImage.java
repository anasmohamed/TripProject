package com.one.direction.nabehha.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.one.direction.nabehha.AppConstants;
import com.one.direction.nabehha.InjectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadImage extends IntentService {

    public static final String TRIP_IMAGE_URL = "tripImage";
    public static final String TRIP_ID = "tripId";

    public DownloadImage() {
        super("DownloadImage");
    }

    public static void startDownloadAndSaveInDb(Context context, String imageUrl, String tripId) {
        Intent intent = new Intent(context, DownloadImage.class);
        intent.putExtra(TRIP_IMAGE_URL, imageUrl);
        intent.putExtra(TRIP_ID, tripId);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String url = intent.getStringExtra(TRIP_IMAGE_URL);
            final String tripId = intent.getStringExtra(TRIP_ID);
            downloadImageAndSaveToDb(url, tripId);

        }
    }

    private void downloadImageAndSaveToDb(String url, String tripId) {
        Log.e("DownloADServices"," method");
        byte[] imageByte;
        imageByte = getbyteArrayFromURL(url);
        InjectionUtils.provideTripRepository(this).saveTripImage(tripId, imageByte);
        if (imageByte != null) {
            Log.e("DownloADServices"," get image");
            Intent in = new Intent();
            in.putExtra(TRIP_IMAGE_URL, imageByte);
            in.putExtra(TRIP_ID, tripId);
            in.setAction(AppConstants.RECEIVE_IMAGE_ACTION);
            LocalBroadcastManager.getInstance(this).sendBroadcast(in);
            /*TODO in trip viewModel register this code to receive image to render

            private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(intent!=null){
                        intent.getLongExtra(DownloadImage.TRIP_ID,-1);
                        intent.getByteArrayExtra(DownloadImage.TRIP_IMAGE_URL);
                    }
                }
            }


            @Override
            protected void onResume ()
            {
                super.onResume();
                //registerReceiver(broadcastReceiver,mIntent);
                LocalBroadcastManager.getInstance(this)
                        .registerReceiver(broadcastReceiver, new IntentFilter(AppConstants.RECEIVE_IMAGE_ACTION));
            }

            @Override
            protected void onPause () {
                if (mIntent != null) {
                    unregisterReceiver(broadcastReceiver);
                    mIntent = null;
                }
                super.onPause();
            }
             *
             *
             *
             * */
        }

    }

    public byte[] getbyteArrayFromURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return readBytes(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }

}
