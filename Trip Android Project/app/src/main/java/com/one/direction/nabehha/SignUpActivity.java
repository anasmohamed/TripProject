package com.one.direction.nabehha;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.one.direction.nabehha.ui.signin.SignInFragment;
import com.one.direction.nabehha.ui.signup.SignUpFragment;

import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity implements SwapFragment {

    boolean isLoginfragment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, SignUpFragment.newInstance())
//                    .commitNow();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignInFragment.newInstance())
                    .commitNow();
        }


//
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), "AIzaSyAmRud_cObFexmvQJapkvC3oFui8B2EIWY");
//        }
//
//// Initialize the AutocompleteSupportFragment.
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                place.getPhotoMetadatas();
//                place.getAddress();
//            }
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//
//            }
//        });
//
//        Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);


    }

    @Override
    public void swapFragment() {
        if (isLoginfragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignUpFragment.newInstance())
                    .commitNow();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignInFragment.newInstance())
                    .commitNow();
        }
        isLoginfragment = !isLoginfragment;
    }

    @Override
    public void onBackPressed() {
        if (isLoginfragment)
            super.onBackPressed();
        else
            swapFragment();

    }
}
