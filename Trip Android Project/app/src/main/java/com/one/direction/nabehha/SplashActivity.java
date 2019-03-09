package com.one.direction.nabehha;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.one.direction.nabehha.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding mActivitySplashBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding=DataBindingUtil.setContentView(this, R.layout.activity_splash);
        UserPreferencesHelper userPreferencesHelper=new AppPreferencesHelper(this,getString(R.string.user_info));
        AppConstants.CURRENT_USER_EMAIL=userPreferencesHelper.getCurrentUserEmail();
        if(AppConstants.CURRENT_USER_EMAIL!=null){
            AppConstants.CURRENT_USER_NAME=userPreferencesHelper.getCurrentUserName();
            AppConstants.CURRENT_USER_ID=userPreferencesHelper.getCurrentUserId();
            //TODO Go To Main Screen

        }
        else{
            startActivity(new Intent(this,LoginActivity.class));
        }
        finish();
    }
}