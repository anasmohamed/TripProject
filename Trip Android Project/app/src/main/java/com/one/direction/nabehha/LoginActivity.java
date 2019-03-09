package com.one.direction.nabehha;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.one.direction.nabehha.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "<^_^>";
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;

    private ActivityLoginBinding mActivityLoginBinding;
    private AppExecutors mAppExecutors;
    private UserPreferencesHelper userPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userPreferencesHelper = new AppPreferencesHelper(this, getString(R.string.user_info));
        mAppExecutors = AppExecutors.getInstance();
        mActivityLoginBinding.signInGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingUi(true);
                GoogleSignIn();
            }
        });
        mActivityLoginBinding.emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingUi(true);
                mAppExecutors.networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        //TODO WS LogIn

//                        User user = WS.login(mActivityLoginBinding.email.getText().toString()
//                                , mActivityLoginBinding.password.getText().toString());


//                       goToTripsHome(user);
                    }
                });
            }
        });

    }

    private void goToTripsHome(/*User user*/) {
        mAppExecutors.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                loadingUi(false);
//                                if (user.getEmail() != null) {
//                                    passUser(user);
//                                    //TODO Go To Main Screen
//                                } else {
//                                    Toast.makeText(LoginActivity.this,
//                                            "You Are Not Registered or wrong password",
//                                            Toast.LENGTH_SHORT).show();
//                                }

            }
        });
    }


    private void GoogleSignIn() {
        if (mGoogleApiClient == null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Failed to sign in", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            if (acct != null) {
                String personName = acct.getDisplayName();
                String email = acct.getEmail();

                Intent profile = new Intent(this, SplashActivity.class);
                startActivity(profile);

                //TODO WS LogIn ,, in ws check if email exist if not reqist all case return user
                //ً  User user=WS.googleLogin(email,personName)
//                goToTripsHome(user);
            }


        } else {
            loadingUi(false);
            Toast.makeText(LoginActivity.this,
                                            "Can't complete",
                                            Toast.LENGTH_SHORT).show();
        }
    }

    private void loadingUi(boolean isLoading) {
        if (isLoading) {
            mActivityLoginBinding.loginProgress.setVisibility(View.VISIBLE);
        } else {
            mActivityLoginBinding.loginProgress.setVisibility(View.GONE);
        }
        mActivityLoginBinding.emailSignInButton.setEnabled(!isLoading);
        mActivityLoginBinding.signInGoogleButton.setEnabled(!isLoading);
    }

    private void passUser(/*User user*/) {
        /*
        userPreferencesHelper.setCurrentUserId(user.getId());
        userPreferencesHelper.setCurrentUserEmail(user.getEmail);
        userPreferencesHelper.setCurrentUserName(user.getName);

        */
    }

}
