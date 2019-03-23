package com.one.direction.nabehha.ui.signin;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.MainActivity;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.SwapFragment;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.databinding.SigninFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "<^_^>";
    private static final int RC_SIGN_IN = 007;
    SigninFragmentBinding binding;
    private SignInViewModel mViewModel;
    private GoogleApiClient mGoogleApiClient;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.signin_fragment, container, false);
        View view = binding.getRoot();

        binding.signInGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingUi(true);
                GoogleSignIn();
            }
        });
//        binding.emailSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadingUi(true);
//                mViewModel.login(binding.email.getText().toString()
//                        , binding.password.getText().toString(), new Callback<User>() {
//
//                            @Override
//                            public void onResponse(Call<User> call, Response<User> response) {
//                                if (response.body() != null)
//                                    goToTripsHome(response.body());
//                            }
//
//                            @Override
//                            public void onFailure(Call<User> call, Throwable t) {
//
//                            }
//                        });
//            }
//        });
        binding.emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.email.getText().toString().isEmpty() || binding.password.getText().toString().isEmpty()) {
                    mViewModel.login(binding.email.getText().toString(), binding.password.getText().toString(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
//TODO go to main activity
                                }
                            }, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Login Fail Please Check Email Or Password", Toast.LENGTH_SHORT);
                                }
                            }

                    );
                }

            }

        });
        binding.registerSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof SwapFragment)
                    ((SwapFragment) getContext()).swapFragment();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SignInModelFactory factory = InjectionUtils.provideSignInViewModelFactory(getContext());

        mViewModel = ViewModelProviders.of(this, factory).get(SignInViewModel.class);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(), "Failed to sign in", Toast.LENGTH_SHORT).show();
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

    private void goToTripsHome(final User user) {
        loadingUi(false);
        if (user.getEmail() != null) {
            mViewModel.saveUserInfo(user);
            startActivity(new Intent(getActivity(), MainActivity.class));

        } else {
            Toast.makeText(getContext(),
                    "You Are Not Registered or wrong password",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void GoogleSignIn() {
        if (mGoogleApiClient == null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .enableAutoManage(getActivity(), this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess() + "    code : " + result.getStatus().getStatusCode());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            if (acct != null) {
                String personName = acct.getDisplayName();
                String email = acct.getEmail();
                mViewModel.googleLogin(email, personName, new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null)
                            goToTripsHome(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        } else {
            loadingUi(false);
            Toast.makeText(getContext(),
                    "Can't complete",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void loadingUi(boolean isLoading) {
        if (isLoading) {
            binding.loginProgress.setVisibility(View.VISIBLE);
        } else {
            binding.loginProgress.setVisibility(View.GONE);
        }
        binding.emailSignInButton.setEnabled(!isLoading);
        binding.signInGoogleButton.setEnabled(!isLoading);
    }


}
