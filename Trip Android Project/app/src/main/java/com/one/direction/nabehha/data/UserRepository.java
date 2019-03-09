package com.one.direction.nabehha.data;

import android.util.Log;

import com.one.direction.nabehha.data.network.UserWebService;

public class UserRepository {
    private static UserRepository userRepositoryInstance;
    private final UserWebService userWebService;


    public UserRepository(UserWebService userWebService) {
        this.userWebService = userWebService;
    }

    public synchronized static UserRepository getInstance(
             UserWebService userWebService) {
        if (userRepositoryInstance == null) {
            synchronized (LOCK) {
                userRepositoryInstance = new UserRepository( userWebService,
                        );
            }
        }
        return userRepositoryInstance;
    }
}
