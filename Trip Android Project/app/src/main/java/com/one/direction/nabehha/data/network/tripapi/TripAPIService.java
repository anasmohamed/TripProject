package com.one.direction.nabehha.data.network.tripapi;

import com.one.direction.nabehha.data.database.model.Trip;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TripAPIService {
    @POST("addTrip")
    Call<Trip> insertTripIntoWebService(@Body Trip trip);

}
