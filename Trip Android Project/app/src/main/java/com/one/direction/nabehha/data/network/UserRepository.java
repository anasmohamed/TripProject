package com.one.direction.nabehha.data.network;


import com.one.direction.nabehha.AppExecutors;
import com.one.direction.nabehha.UserPreferencesHelper;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.data.network.userapi.UserAPIService;

import retrofit2.Callback;

public class UserRepository {
    private static final Object LOCK = new Object();
    private static UserRepository userRepositoryInstance;
    private final AppExecutors mExecutors;
    private UserAPIService userAPIService;
    private UserPreferencesHelper userPreferencesHelper;


    public UserRepository(UserAPIService userWebService, AppExecutors executors, UserPreferencesHelper userPreferencesHelper) {
        this.userAPIService = userWebService;
        this.mExecutors = executors;
        this.userPreferencesHelper = userPreferencesHelper;
    }

    public synchronized static UserRepository getInstance(
            UserAPIService userAPIService, AppExecutors executors, UserPreferencesHelper userPreferencesHelper) {
        if (userRepositoryInstance == null) {
            synchronized (LOCK) {
                userRepositoryInstance = new UserRepository(userAPIService,
                        executors, userPreferencesHelper);
            }
        }
        return userRepositoryInstance;
    }


    public void insertUserIntoWebService(String userName, String email, String password, Callback<User> mUserCallback) {
        userAPIService.insertUserIntoWebService(new User(userName, email, password)).enqueue(mUserCallback);
    }

    public void login(String email, String password, Callback<User> mUserCallback) {
        userAPIService.login(email, password).enqueue(mUserCallback);
    }

    public void googleLogin(String email, String userName, Callback<User> mUserCallback) {
                userAPIService.googleLogin(userName,email).enqueue(mUserCallback);

    }

    public void saveUserInfo(User user) {
        userPreferencesHelper.setCurrentUserId(user.getUserId());
        userPreferencesHelper.setCurrentUserEmail(user.getEmail());
        userPreferencesHelper.setCurrentUserName(user.getUserName());
    }

    public void saveTripImage(Long tripId,byte[] imageByte) {
//        tripDao.addImageToTrip(tripId,imageByte);
    }
}
