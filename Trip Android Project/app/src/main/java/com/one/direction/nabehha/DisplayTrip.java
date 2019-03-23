package com.one.direction.nabehha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import  android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.direction.nabehha.data.database.model.Trip;

public class DisplayTrip extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RecyclerView notesRecyclerView ;
    ImageView tripImage;
    TextView tripStartTv;
    TextView tripEndTv;
    TextView dateTv;
    TextView timeTv;
    Trip trip ;
    FloatingActionButton floatingActionButton;

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
        floatingActionButton.setOnClickListener(this);

        //get object
        Intent intent = getIntent();
        trip = intent.getParcelableExtra(MainActivity.DISPLAY_ACTIVITY_INTENT);
        tripStartTv.setText(trip.getStartPointAddress());
        tripEndTv.setText(trip.getEndPointAddress());
        dateTv.setText(trip.getDate());
        timeTv.setText(trip.getTime());

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
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter();
        notesRecyclerView.setAdapter(notesRecyclerViewAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.editBtn){
            Intent intent = new Intent(this, EditTrip.class);
            intent.putExtra(DISPLAY_TRIP_OBJECT, trip);
            startActivity(intent);
        }
    }
}
