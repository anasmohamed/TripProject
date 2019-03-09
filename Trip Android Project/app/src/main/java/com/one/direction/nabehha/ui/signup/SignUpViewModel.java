package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModel;

import com.one.direction.nabehha.data.UserRepository;

public class SignUpViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final UserRepository mUserRepository;

    public SignUpViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;

    }
}
