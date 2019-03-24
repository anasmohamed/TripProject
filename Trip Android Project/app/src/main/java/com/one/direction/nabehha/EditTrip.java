package com.one.direction.nabehha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.one.direction.nabehha.data.database.model.Trip;

public class EditTrip extends AppCompatActivity implements View.OnClickListener {

    EditText tripNameET;
    EditText tripTimeET;
    EditText tripDateET;
    Button tripTimeBtn;
    Button tripDateBtn;
    Button cancelBtn;
    Button addTripBtn;
    Button addNoteBtn;
    Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        tripNameET = findViewById(R.id.tripNameET);
        tripTimeET = findViewById(R.id.tripTimeET);
        tripDateET = findViewById(R.id.tripDateET);
        tripTimeBtn = findViewById(R.id.tripTimeBtn);
        tripDateBtn = findViewById(R.id.tripDateBtn);
        cancelBtn = findViewById(R.id.cancelNote);
        addTripBtn = findViewById(R.id.addTripBtn);
        addNoteBtn = findViewById(R.id.addNoteBtn);
        Intent intent = getIntent();
        trip = intent.getParcelableExtra(DisplayTrip.DISPLAY_TRIP_OBJECT);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancelNote:

                break;
            case R.id.addTripBtn:

                break;
            case R.id.addNoteBtn:

                break;
        }
    }
}
