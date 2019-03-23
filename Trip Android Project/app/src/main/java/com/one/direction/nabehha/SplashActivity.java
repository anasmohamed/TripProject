package com.one.direction.nabehha;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;

    FirebaseDatabase mUsersFirebaseDatabaseDatabaseReference;
    DatabaseReference userDatabaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        final UserPreferencesHelper userPreferencesHelper = new AppPreferencesHelper(this, getString(R.string.user_info));
        mAuth = FirebaseAuth.getInstance();
        mUsersFirebaseDatabaseDatabaseReference = FirebaseDatabase.getInstance();
        userDatabaseReference = mUsersFirebaseDatabaseDatabaseReference.getReference("Users");
        if(mAuth.getCurrentUser()!=null) {
            AppConstants.CURRENT_USER_EMAIL = mAuth.getCurrentUser().getEmail();
        }
        //finish();
        countDownTimer = new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (AppConstants.CURRENT_USER_EMAIL != null) {
                    AppConstants.CURRENT_USER_NAME = mAuth.getCurrentUser().getDisplayName();
                    AppConstants.CURRENT_USER_ID = mAuth.getUid();
                    //TODO Go To Main Screen
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
                }
                finish();
            }

        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK)
                //If permission granted start floating widget service
                finish();
            else
                //Permission is not available then display toast
                Toast.makeText(this,
                        "nothing",
                        Toast.LENGTH_SHORT).show();

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onResume() {
        countDownTimer.start();
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 100);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
}
