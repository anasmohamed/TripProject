package com.one.direction.nabehha.ui.signin;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.one.direction.nabehha.data.network.UserRepository;

public class SignInModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final UserRepository mRepository;

    public SignInModelFactory(UserRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SignInViewModel(mRepository);
    }
}
