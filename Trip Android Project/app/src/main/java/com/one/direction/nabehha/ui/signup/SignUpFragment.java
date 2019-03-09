package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.data.network.UserAPIService;
import com.one.direction.nabehha.data.network.UserAPIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    private EditText signUpFullNameET;
    private EditText signUpEmailET;
    private EditText signUpPasswordET;
    private Button signUpBtn;
    private UserAPIService mUserAPIService;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SignUpModelFactory factory = InjectionUtils.provideSignUpViewModelFactory(getContext());

        mViewModel = ViewModelProviders.of(this,factory).get(SignUpViewModel.class);
        signUpFullNameET = getView().findViewById(R.id.fullNameSignUpET);
        signUpEmailET = getView().findViewById(R.id.emailSignUpET);
        signUpPasswordET = getView().findViewById(R.id.passwordSignUpET);
        signUpBtn = getView().findViewById(R.id.signUpBtn);
        mUserAPIService = UserAPIUtils.geUserAPIService();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendPost(signUpEmailET.getText().toString(),signUpPasswordET.getText().toString());
            }
        });
        // TODO: Use the ViewModel

    }

    public void sendPost(String title, String body) {
        mUserAPIService.savePost(title, body, "anas").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(getContext(),"onResponse",Toast.LENGTH_SHORT).show();
                    Log.i("View Model", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(),"onFailure"+t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i("View Model", "Unable to submit post to API. "+t.getMessage() + "    "+t.getCause().toString() + " " + t.toString());
            }
        });
    }


}
