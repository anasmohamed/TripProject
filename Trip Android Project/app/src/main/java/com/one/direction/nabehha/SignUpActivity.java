package com.one.direction.nabehha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.one.direction.nabehha.ui.signup.RegisterFragment;
import com.one.direction.nabehha.ui.signup.SignUpFragment;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignUpFragment.newInstance())
                    .commitNow();
        }
    }
}
