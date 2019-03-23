package com.one.direction.nabehha.data.network.tripapi;

import com.one.direction.nabehha.data.database.model.Trip;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TripAPIService {
    @POST("addTrip")
    Call<Trip> insertTripIntoWebService(@Body Trip trip);

    @GET("getTrips")
    Call<List<Trip>> getTrips(@Query("userId") String userId , @Query("tripType") String tripStatus );

    @DELETE("deleteTrip")
    Call<Trip> deleteTrips(@Query("tripId") String tripId);
}
