package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.AuthResult;
import com.one.direction.nabehha.MainActivity;
import com.one.direction.nabehha.data.network.UserRepository;
import com.one.direction.nabehha.data.database.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final UserRepository mUserRepository;


    public SignUpViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    public void sendUserDataToWebService(String userName, String email, String password,Callback<User> mUserCallback) {

        mUserRepository.insertUserIntoWebService(userName, email, password,mUserCallback );


    }


    public void register(String email, String password, String userName, OnCompleteListener<AuthResult>authResultOnCompleteListener, OnFailureListener onFailureListener)
    {
        mUserRepository.register(email,password,userName,authResultOnCompleteListener,onFailureListener);
    }


    public void saveUserInfo(User user) {
        mUserRepository.saveUserInfo(user);
    }
}
