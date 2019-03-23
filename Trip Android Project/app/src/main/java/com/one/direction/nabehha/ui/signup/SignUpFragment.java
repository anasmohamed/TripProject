package com.one.direction.nabehha.ui.signup;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.MainActivity;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.SignUpActivity;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.databinding.SignupFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    SignupFragmentBinding binding;
    FirebaseDatabase mUsersFirebaseDatabaseDatabaseReference;
    DatabaseReference userDatabaseReference;
    private FirebaseAuth mAuth;
    private SignUpViewModel mViewModel;


    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.signup_fragment, container, false);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        mUsersFirebaseDatabaseDatabaseReference = FirebaseDatabase.getInstance();
        userDatabaseReference = mUsersFirebaseDatabaseDatabaseReference.getReference("Users");

//        //here data must be an instance of the class MarsDataProvider
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SignUpModelFactory factory = InjectionUtils.provideSignUpViewModelFactory(getContext());

        mViewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel.class);
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadingUi(true);

                // mViewModel.sendUserDataToWebService(binding.fullNameSignUpET.getText().toString(),binding.emailSignUpET.getText().toString(), binding.passwordSignUpET.getText().toString());
                // createAccount(binding.emailSignUpET.getText().toString(), binding.passwordSignUpET.getText().toString());
                mViewModel.register(binding.emailSignUpET.getText().toString(), binding.passwordSignUpET.getText().toString(), binding.fullNameSignUpET.getText().toString(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        User user = new User();
                        user.setUserId(mAuth.getCurrentUser().getUid());
                        user.setUserName(binding.emailSignUpET.getText().toString().substring(0, binding.emailSignUpET.getText().toString().indexOf("@")));
                        userDatabaseReference.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getActivity(), "COrrrect", Toast.LENGTH_SHORT).show();
                                goToTripsHome(response.body());

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "There is Error in Email or Password", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "There is Error in Email or Password", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    private void goToTripsHome(final User user) {
//        loadingUi(false);
        if (user.getEmail() != null) {
            mViewModel.saveUserInfo(user);
            startActivity(new Intent(getActivity(), MainActivity.class));

        } else {
            Toast.makeText(getContext(),
                    "You Are Not Registered or wrong password",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void loadingUi(boolean isLoading) {
        if (isLoading) {
            binding.loginProgress.setVisibility(View.VISIBLE);
        } else {
            binding.loginProgress.setVisibility(View.GONE);
        }
        binding.emailSignUpET.setEnabled(!isLoading);
        binding.fullNameSignUpET.setEnabled(!isLoading);
        binding.passwordSignUpET.setEnabled(!isLoading);
        binding.signUpBtn.setEnabled(!isLoading);
    }

}
