package com.one.direction.nabehha;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.one.direction.nabehha.databinding.ActivitySplashScreenBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding mActivitySplashBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding=DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        UserPreferencesHelper userPreferencesHelper=new AppPreferencesHelper(this,getString(R.string.user_info));
        AppConstants.CURRENT_USER_EMAIL=userPreferencesHelper.getCurrentUserEmail();
        if(AppConstants.CURRENT_USER_EMAIL!=null){
            AppConstants.CURRENT_USER_NAME=userPreferencesHelper.getCurrentUserName();
            AppConstants.CURRENT_USER_ID=userPreferencesHelper.getCurrentUserId();
            //TODO Go To Main Screen

        }
        else{
            startActivity(new Intent(this,SignUpActivity.class));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 100);
        }
        //finish();
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
}
