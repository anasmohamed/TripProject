package com.one.direction.nabehha.data.network;

import com.one.direction.nabehha.data.database.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPIService {
    @POST("post")
    Call<User> insertUserIntoWebService(@Body User user);
}
