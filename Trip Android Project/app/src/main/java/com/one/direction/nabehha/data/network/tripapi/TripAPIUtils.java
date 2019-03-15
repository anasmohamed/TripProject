package com.one.direction.nabehha.data.network.tripapi;

public class TripAPIUtils {
    private TripAPIUtils() {}

    public static final String BASE_URL = "http://10.0.2.2:8084/TripWebService/dao/hello/";

    public static TripAPIService geTripAPIService() {

        return TripRetrofitClient.getClient(BASE_URL).create(TripAPIService.class);
    }
}
