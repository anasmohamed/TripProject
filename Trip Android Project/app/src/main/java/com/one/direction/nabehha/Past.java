package com.one.direction.nabehha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.databinding.PastBinding;
import com.one.direction.nabehha.webServiceUtils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Past extends Fragment {

    RecyclerView tripRecyclerView;
    TripRecyclerViewAdapter tripAdapter;
    List<Trip> trips = null;
    private static final String TRIP_STATUS = "past";
    PastBinding mPastBinding;

    public Past() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.past, container, false);
        tripRecyclerView = view.findViewById(R.id.tripRecyclerView);
        allTripGoogleImageImg = view.findViewById(R.id.all_trip_google_image_Img);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        tripRecyclerView.setLayoutManager(layoutManager);
        tripRecyclerView.setHasFixedSize(true);
        RetrofitUtils retrofitUtils = new RetrofitUtils();
        retrofitUtils.getTripsUsingRetrofit("2", TRIP_STATUS, new Callback<List<Trip>>() {
            @Override

            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (!response.isSuccessful()) {
                    //Log.e(HTTP_CODE, String.valueOf(response.code()));
                    return;
                }
                trips = response.body();
                tripAdapter = new TripRecyclerViewAdapter(trips);
                tripRecyclerView.setAdapter(tripAdapter);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                // Log.e(RETROFIT_ERROR, t.getMessage());
            }
        });
//        Picasso.get()
//                .load(Utilities.getGoogleMapImageForTrips(trips))
//                .placeholder(R.drawable.ic_close_black_24dp)
//                .error(R.drawable.ic_close_white_24dp)
//                .into(allTripGoogleImageImg);

        return view;

//        mPastBinding = DataBindingUtil.inflate(inflater, R.layout.past, container, false);
//        //TODO  implement viewmodel
////        Picasso.get()
////                .load(new URL(Utilities.getGoogleMapImageForTrips(mPastViewModel.getTripList())))
////                .placeholder(R.drawable.ic_close_black_24dp)
////                .error(R.drawable.ic_close_white_24dp)
////                .into(mPastBinding.allTripGoogleImageImg);
//

//        return mPastBinding.getRoot();
    }
}
