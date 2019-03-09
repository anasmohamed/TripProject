package com.one.direction.nabehha.data;


import com.one.direction.nabehha.AppExecutors;
import com.one.direction.nabehha.data.network.UserAPIService;

public class UserRepository {
    private static final Object LOCK = new Object();
    private static UserRepository userRepositoryInstance;
    private final AppExecutors mExecutors;
    UserAPIService userAPIService;

    public UserRepository(UserAPIService userWebService, AppExecutors executors) {
        this.userAPIService = userWebService;
        this.mExecutors = executors;
    }

    public synchronized static UserRepository getInstance(
            UserAPIService userAPIService, AppExecutors executors) {
        if (userRepositoryInstance == null) {
            synchronized (LOCK) {
                userRepositoryInstance = new UserRepository(userAPIService,
                        executors);
            }
        }
        return userRepositoryInstance;
    }
}
