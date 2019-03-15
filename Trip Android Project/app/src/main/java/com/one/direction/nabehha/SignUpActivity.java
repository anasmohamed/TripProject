package com.one.direction.nabehha;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.one.direction.nabehha.ui.signin.SignInFragment;
import com.one.direction.nabehha.ui.signup.SignUpFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import static com.one.direction.nabehha.AppConstants.BASE_GOOGLE_IMAGE;

public class SignUpActivity extends AppCompatActivity implements SwapFragment {

    boolean isLoginfragment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
//        if (savedInstanceState == null) {
////            getSupportFragmentManager().beginTransaction()
////                    .replace(R.id.container, SignUpFragment.newInstance())
////                    .commitNow();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, SignInFragment.newInstance())
//                    .commitNow();
//        }
//

//
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyC9VkN6sukOJDJlFlD1gL9PGW9wZQgM4bw");
        }

// Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                getbyteArrayFromURL(BASE_GOOGLE_IMAGE+place.getPhotoMetadatas().get(0).a()) ;
                place.getAddress();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

            }
        });
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



    public byte[] getbyteArrayFromURL(String src) {
        try {
            URL url = new URL(src);
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
