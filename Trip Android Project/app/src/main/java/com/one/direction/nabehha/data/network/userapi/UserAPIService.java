package com.one.direction.nabehha.data.network.userapi;

import com.one.direction.nabehha.data.database.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPIService {
    @POST("post")
    Call<User> insertUserIntoWebService(@Body User user);
}
