package com.one.direction.nabehha.data.network;

import com.one.direction.nabehha.data.database.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPIService {
    @POST("/test")
    @FormUrlEncoded
    Call<User> insertUserIntoWebService(@Field("email") String email,
                        @Field("userName") String userName,
                        @Field("password") String password);
}
