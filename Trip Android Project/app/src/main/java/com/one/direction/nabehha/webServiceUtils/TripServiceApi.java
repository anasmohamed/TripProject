package com.one.direction.nabehha.webServiceUtils;

import com.one.direction.nabehha.data.database.model.TripModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TripServiceApi {

    @GET("getTrips")
    Call<List<TripModel>> getTrips(@Query("userId") String userId , @Query("tripType") String tripStatus );

    @DELETE("deleteTrip")
    Call<TripModel> deleteTrips(@Query("tripId") String tripId);
}
