package com.one.direction.nabehha.webServiceUtils;

import com.one.direction.nabehha.data.database.model.Trip;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static final String BASE_URL = "http://10.0.2.2:8084/TripWebService/tripservice/trip/";

    private Retrofit retrofit;
    private TripServiceApi tripServiceApi;
    private List<Trip> trips;

    public RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tripServiceApi = retrofit.create(TripServiceApi.class);
    }

    public void getTripsUsingRetrofit(String userId, String tripStatus, Callback<List<Trip>> mListCallback) {

        Call<List<Trip>> call = tripServiceApi.getTrips(userId, tripStatus);
        call.enqueue(mListCallback);
    }

    public Call<Trip> deleteTripsUsingRetrofit(String tripId, Callback<Boolean> mListCallback ) {
        Call<Trip> response = tripServiceApi.deleteTrips(tripId);
        return response;
    }

}
