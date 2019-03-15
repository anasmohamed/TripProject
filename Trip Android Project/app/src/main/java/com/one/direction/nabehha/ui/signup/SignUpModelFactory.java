package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.one.direction.nabehha.data.network.UserRepository;

public class SignUpModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private final UserRepository mRepository;

    public SignUpModelFactory(UserRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SignUpViewModel(mRepository);
    }
}
