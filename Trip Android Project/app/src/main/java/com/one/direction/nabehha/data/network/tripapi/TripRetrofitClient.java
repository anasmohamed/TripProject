package com.one.direction.nabehha.data.network.tripapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TripRetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
