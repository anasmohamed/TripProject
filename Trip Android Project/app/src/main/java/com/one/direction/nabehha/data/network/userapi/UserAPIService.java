package com.one.direction.nabehha.data.network.userapi;

import com.one.direction.nabehha.data.database.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPIService {
    @POST("post")
    Call<User> insertUserIntoWebService(@Body User user);

    @POST("login")
    Call<User> login(@Query("userEmail") String email, @Query("password") String password);

    @POST("registerWithGmail")
    Call<User> googleLogin(@Query("userName") String userName, @Query("userEmail") String email);
}
