package com.one.direction.nabehha.data.network.userapi;


public class UserAPIUtils {
    private UserAPIUtils() {}

    public static final String BASE_URL = "http://10.0.2.2:8084/TripWebService/dao/hello/";

    public static UserAPIService geUserAPIService() {

        return UserRetrofitClient.getClient(BASE_URL).create(UserAPIService.class);
    }
}
