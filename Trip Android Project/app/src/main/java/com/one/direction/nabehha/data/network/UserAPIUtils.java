package com.one.direction.nabehha.data.network;

public class UserAPIUtils {
    private UserAPIUtils() {}

    public static final String BASE_URL = "http://localhost:9090/TripWebService/dao/hello/";

    public static UserAPIService geUserAPIService() {

        return UserRetrofitClient.getClient(BASE_URL).create(UserAPIService.class);
    }
}
