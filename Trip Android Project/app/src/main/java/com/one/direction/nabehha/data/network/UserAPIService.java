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
    Call<User> savePost(@Field("e_mail") String email,
                        @Field("password") String password,
                        @Field("userName") String userName);
}
