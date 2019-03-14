package com.one.direction.nabehha;

import android.content.Context;

import com.one.direction.nabehha.data.UserRepository;
import com.one.direction.nabehha.data.network.UserAPIService;
import com.one.direction.nabehha.data.network.UserAPIUtils;
import com.one.direction.nabehha.ui.signin.SignInModelFactory;
import com.one.direction.nabehha.ui.signup.SignUpModelFactory;

public class InjectionUtils {
    public static UserRepository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        UserAPIService userAPIService = UserAPIUtils.geUserAPIService();
        UserPreferencesHelper userPreferencesHelper = new AppPreferencesHelper(context, context.getString(R.string.user_info));
        return UserRepository.getInstance(userAPIService, executors,userPreferencesHelper);
    }

    public static SignUpModelFactory provideSignUpViewModelFactory(Context context) {
        UserRepository repository = provideRepository(context.getApplicationContext());
        return new SignUpModelFactory(repository);
    }
    public static SignInModelFactory provideSignInViewModelFactory(Context context) {
        UserRepository repository = provideRepository(context.getApplicationContext());
        return new SignInModelFactory(repository);
    }
}
