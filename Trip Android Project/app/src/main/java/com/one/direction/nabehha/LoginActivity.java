package com.one.direction.nabehha;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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


                        mAppExecutors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                loadingUi(false);
//                                if (user.getEmail() != null) {
//                                    passUser(user);
//                                } else {
//                                    Toast.makeText(LoginActivity.this,
//                                            "You Are Not Registered or wrong password",
//                                            Toast.LENGTH_SHORT).show();
//                                }

                            }
                        });
                    }
                });
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
        Toast.makeText(this, "Faild to sign in", Toast.LENGTH_SHORT).show();
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
        Log.d(TAG, "handleSignInResult:" + result.isSuccess() +"   code: "+result.getStatus().getStatusCode());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            if (acct != null) {
                String personName = acct.getDisplayName();
                String personPhotoUrl = acct.getPhotoUrl().toString();
                String email = acct.getEmail();

                Intent profile = new Intent(this, SplashActivity.class);
//            profile.putExtra(getString(R.string.user_name), personName);
//            profile.putExtra(getString(R.string.user_email), email);
//            profile.putExtra(getString(R.string.user_photo), personPhotoUrl);
                startActivity(profile);

                //TODO WS LogIn
                //if not exist : Register
            }


        } else {
            // Signed out, show unauthenticated UI.
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
         //TODO Go To Main Screen
        */
    }


    @Override
    public void onStart() {
        super.onStart();
//        if (mGoogleApiClient != null) {
//            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//            if (opr.isDone()) {
//                // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//                // and the GoogleSignInResult will be available instantly.
//                Log.e(TAG, "Got cached sign-in");
//                GoogleSignInResult result = opr.get();
//                handleSignInResult(result);
//            } else {
//                // If the user has not previously signed in on this device or the sign-in has expired,
//                // this asynchronous branch will attempt to sign in the user silently.  Cross-device
//                // single sign-on will occur in this branch.
////            showProgressDialog();
//                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                    @Override
//                    public void onResult(GoogleSignInResult googleSignInResult) {
////                    hideProgressDialog();
//                        Log.e(TAG, "Gotn't cached sign-in");
//                        handleSignInResult(googleSignInResult);
//                    }
//                });
//            }
//        }
    }

}
