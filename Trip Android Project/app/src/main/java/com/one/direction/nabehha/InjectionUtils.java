package com.one.direction.nabehha;

import android.content.Context;

import com.one.direction.nabehha.data.database.TripDataBase;
import com.one.direction.nabehha.data.database.dao.TripDao;
import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.network.TripRepository;
import com.one.direction.nabehha.data.network.UserRepository;
import com.one.direction.nabehha.data.network.tripapi.TripAPIService;
import com.one.direction.nabehha.data.network.tripapi.TripAPIUtils;
import com.one.direction.nabehha.data.network.userapi.UserAPIService;
import com.one.direction.nabehha.data.network.userapi.UserAPIUtils;
import com.one.direction.nabehha.data.network.userapi.UserFireBase;
import com.one.direction.nabehha.ui.addtrip.AddTripModelFactory;
import com.one.direction.nabehha.ui.signin.SignInModelFactory;
import com.one.direction.nabehha.ui.signup.SignUpModelFactory;

public class InjectionUtils {
    public static UserRepository provideUserRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        UserFireBase userFireBase = UserFireBase.getInstance();
        UserPreferencesHelper userPreferencesHelper = new AppPreferencesHelper(context, context.getString(R.string.user_info));
        return UserRepository.getInstance(userFireBase, executors,userPreferencesHelper);
    }
    public static TripRepository provideTripRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        TripAPIService tripAPIService = TripAPIUtils.geTripAPIService();
        TripDataBase tripDataBase = TripDataBase.getDatabase(context);
        return TripRepository.getInstance(tripAPIService,tripDataBase ,executors);
    }

    public static SignUpModelFactory provideSignUpViewModelFactory(Context context) {
        UserRepository repository = provideUserRepository(context.getApplicationContext());
        return new SignUpModelFactory(repository);
    }
    public static SignInModelFactory provideSignInViewModelFactory(Context context) {
        UserRepository repository = provideUserRepository(context.getApplicationContext());
        return new SignInModelFactory(repository);
    }
    public static AddTripModelFactory provideAddTripViewModelFactory(Context context) {
        TripRepository repository = provideTripRepository(context.getApplicationContext());
        return new AddTripModelFactory(repository);
    }


}
