package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.widget.Toast;

import com.one.direction.nabehha.data.UserRepository;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.data.network.UserAPIService;
import com.one.direction.nabehha.data.network.UserAPIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final UserRepository mUserRepository;
    private UserAPIService mUserAPIService;

    public SignUpViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;
        mUserAPIService = UserAPIUtils.geUserAPIService();


    }
    public void sendUserDataToWebService(String userName, String email,String password) {

        mUserAPIService.insertUserIntoWebService(new User(userName, email, password)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("View Model", "post submitted to API." +response.code());
                if (response.isSuccessful()) {
                    Log.e("View Model", "post submitted to API.  " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("View Model", "Unable to submit post to API. "+t.getMessage() + "    "+t.getCause() + " " + t.toString());
            }
        });
    }

}
