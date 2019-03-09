package com.one.direction.nabehha;

import android.content.Context;

import com.one.direction.nabehha.data.UserRepository;
import com.one.direction.nabehha.data.network.UserAPIService;
import com.one.direction.nabehha.data.network.UserAPIUtils;
import com.one.direction.nabehha.ui.signup.SignUpModelFactory;

public class InjectionUtils {
    public static UserRepository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        UserAPIService userAPIService = UserAPIUtils.geUserAPIService();
        return UserRepository.getInstance(userAPIService, executors);
    }

    public static SignUpModelFactory provideSignUpViewModelFactory(Context context) {
        UserRepository repository = provideRepository(context.getApplicationContext());
        return new SignUpModelFactory(repository);
    }
}
