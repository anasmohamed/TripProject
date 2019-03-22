package com.one.direction.nabehha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import  android.support.v7.widget.Toolbar;

public class DisplayTrip extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView notesRecyclerView ;

    NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trip);

        toolbar = findViewById(R.id.toolbar);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);

        //toolbar settings
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //recyclerView settings
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notesRecyclerView.setLayoutManager(layoutManager);
        notesRecyclerView.setHasFixedSize(true);
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter();
        notesRecyclerView.setAdapter(notesRecyclerViewAdapter);

    }
}
