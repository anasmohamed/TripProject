package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

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

    public SignUpViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    public void sendUserDataToWebService(String userName, String email, String password) {

        mUserRepository.insertUserIntoWebService(userName, email, password, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    //TODO
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("View Model", "Unable to submit post to API. " + t.getMessage() + "    " + t.getCause() + " " + t.toString());
            }
        });

    }

}
