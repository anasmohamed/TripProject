package com.one.direction.nabehha.ui.signin;

import android.arch.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.AuthResult;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.data.network.UserRepository;

import retrofit2.Callback;

public class SignInViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final UserRepository mUserRepository;


    public SignInViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

//    public void login(String email, String password, Callback<User> userCallback) {
//        mUserRepository.login(email, password, userCallback);
//    }

    public void login(String email, String password, OnCompleteListener<AuthResult> authResultOnCompleteListener, OnFailureListener onFailureListener) {
        mUserRepository.login(email, password, authResultOnCompleteListener,onFailureListener);
    }

    public void googleLogin(String email, String userName, Callback<User> userCallback) {
        mUserRepository.googleLogin(email, userName, userCallback);

    }

    public void saveUserInfo(User user) {
        mUserRepository.saveUserInfo(user);
    }

}
