package com.one.direction.nabehha.data.network.userapi;

import com.one.direction.nabehha.data.database.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPIService {
    @GET("register")
    Call<User> insertUserIntoWebService(@Query("userName") String userName,@Query("email") String email, @Query("password") String password);

    @GET("login")
    Call<User> login(@Query("userName") String email, @Query("password") String password);

    @GET("registerWithGmail")
    Call<User> googleLogin(@Query("userName") String userName, @Query("userEmail") String email);
}
