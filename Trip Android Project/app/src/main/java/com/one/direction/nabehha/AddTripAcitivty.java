package com.one.direction.nabehha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.one.direction.nabehha.ui.addtrip.AddTripFragment;

public class AddTripAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip_acitivty);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AddTripFragment.newInstance())
                .commitNow();
    }
}
