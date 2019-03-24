package com.one.direction.nabehha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.service.DownloadImage;
import com.one.direction.nabehha.service.note.FloatingWidgetService;
import com.squareup.picasso.Picasso;

import androidx.work.WorkManager;

public class DisplayTrip extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RecyclerView notesRecyclerView;
    ImageView tripImage;
    TextView tripStartTv;
    TextView tripEndTv;
    TextView dateTv;
    TextView timeTv;
    Trip trip;
    FloatingActionButton floatingActionButton;
    Button startBtn;
    Button cancelBtn;

    NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    public static final String DISPLAY_TRIP_OBJECT = "tripObject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trip);

        toolbar = findViewById(R.id.toolbar);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        tripStartTv = findViewById(R.id.tripStartTv);
        tripEndTv = findViewById(R.id.tripEndTv);
        dateTv = findViewById(R.id.dateTv);
        timeTv = findViewById(R.id.timeTv);
        tripImage = findViewById(R.id.tripImage);
        floatingActionButton = findViewById(R.id.editBtn);
        startBtn = findViewById(R.id.startBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        startBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);

        //get object
        Intent intent = getIntent();
        trip = intent.getParcelableExtra(MainActivity.DISPLAY_ACTIVITY_INTENT);
        tripStartTv.setText(trip.getStartPointAddress());
        tripEndTv.setText(trip.getEndPointAddress());
        dateTv.setText(trip.getDate());
        timeTv.setText(trip.getTime());
        Picasso.get()
                .load(Utilities.getGoogleMapImageForTrip(trip))
                .placeholder(R.drawable.ic_not_found)
                .error(R.drawable.ic_close_white_24dp)
                .into(tripImage);

        //toolbar settings
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            DisplayTrip.this.setTitle(trip.getTripName());
        }
        //recyclerView settings
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notesRecyclerView.setLayoutManager(layoutManager);
        notesRecyclerView.setHasFixedSize(true);
        if (trip.getNotes() != null) {
            notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(trip.getNotes());
            notesRecyclerView.setAdapter(notesRecyclerViewAdapter);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.editBtn) {
            Intent intent = new Intent(this, EditTrip.class);
            intent.putExtra(DISPLAY_TRIP_OBJECT, trip);
            startActivity(intent);
        } else if (v.getId() == R.id.startBtn) {
            Toast.makeText(this, "started", Toast.LENGTH_LONG).show();
            startTrip(trip);
        } else if (v.getId() == R.id.cancelBtn) {
            cancelTrip(trip);
        }
    }

    private void cancelTrip(Trip trip) {
        //        InjectionUtils.provideTripRepository(context).changeTripStatus(trip.getId(),"cancel");
        //TODO stop work manger alarm
        WorkManager.getInstance().cancelAllWorkByTag(trip.getTripId());
        finish();
    }

    private void startTrip(Trip trip) {
//        InjectionUtils.provideTripRepository(context).changeTripStatus(trip.getId(),"past");
        //TODO remove alarm for this trip

        WorkManager.getInstance().cancelAllWorkByTag(trip.getTripId());//TODO Cancel with id better i think
        startFloatingWidgetService(trip.getTripId());
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + trip.getEndPointLatitude() + "," + trip.getEndPointLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void startFloatingWidgetService(String tripId) {
        Intent intent = new Intent(this, FloatingWidgetService.class);
        intent.putExtra(DownloadImage.TRIP_ID, tripId);
        startService(intent);
    }
}
