package com.one.direction.nabehha.webServiceUtils;

import com.one.direction.nabehha.data.database.model.Trip;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TripServiceApi {

    @GET("getTrips")
    Call<List<Trip>> getTrips(@Query("userId") String userId , @Query("tripStatus") String tripStatus );

    @DELETE("deleteTrip")
    Call<Trip> deleteTrips(@Query("tripId") String tripId);
}
