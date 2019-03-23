package com.one.direction.nabehha.data.network;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.AuthResult;
import com.one.direction.nabehha.AppExecutors;
import com.one.direction.nabehha.UserPreferencesHelper;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.data.network.userapi.UserAPIService;
import com.one.direction.nabehha.data.network.userapi.UserFireBase;

import retrofit2.Callback;

public class UserRepository {
    private static final Object LOCK = new Object();
    private static UserRepository userRepositoryInstance;
    private final AppExecutors mExecutors;
    private UserAPIService userAPIService;
    private UserFireBase userFireBase;
    private UserPreferencesHelper userPreferencesHelper;


    public UserRepository(UserFireBase userFireBase, AppExecutors executors, UserPreferencesHelper userPreferencesHelper) {
        this.userFireBase = userFireBase;
        this.mExecutors = executors;
        this.userPreferencesHelper = userPreferencesHelper;
    }

    public synchronized static UserRepository getInstance(
            UserFireBase userFireBase, AppExecutors executors, UserPreferencesHelper userPreferencesHelper) {
        if (userRepositoryInstance == null) {
            synchronized (LOCK) {
                userRepositoryInstance = new UserRepository(userFireBase,
                        executors, userPreferencesHelper);
            }
        }
        return userRepositoryInstance;
    }


    public void insertUserIntoWebService(String userName, String email, String password, Callback<User> mUserCallback) {
        userAPIService.insertUserIntoWebService(userName, email, password).enqueue(mUserCallback);
    }

    public void login(String email, String password, Callback<User> mUserCallback) {
        userAPIService.login(email, password).enqueue(mUserCallback);
//        userFireBase.login(email,password,);

    }

    public void login(String email, String password, OnCompleteListener<AuthResult> authResultOnCompleteListener, OnFailureListener onFailureListener) {
        // userAPIService.login(email, password).enqueue(mUserCallback);
        userFireBase.login(email, password, authResultOnCompleteListener, onFailureListener);

    }

    public void googleLogin(String email, String userName, Callback<User> mUserCallback) {
        userAPIService.googleLogin(userName, email).enqueue(mUserCallback);

    }

    public void register(String email, String passeword, String userName, OnCompleteListener<AuthResult> authResultOnCompleteListener, OnFailureListener onFailureListener) {
        userFireBase.register(email, passeword, userName, authResultOnCompleteListener, onFailureListener);
    }

    public void saveUserInfo(User user) {
        // userPreferencesHelper.setCurrentUserId(user.getUserId());
        userPreferencesHelper.setCurrentUserEmail(user.getEmail());
        userPreferencesHelper.setCurrentUserName(user.getUserName());
    }

    public void saveTripImage(Long tripId, byte[] imageByte) {
//        tripDao.addImageToTrip(tripId,imageByte);
    }
}
