package com.one.direction.nabehha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.data.database.model.TripModel;
import com.one.direction.nabehha.webServiceUtils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Trash extends Fragment {

    RecyclerView tripRecyclerView;
    TripRecyclerViewAdapter tripAdapter;
    List<TripModel> trips = null;
    private static final String TRIP_STATUS = "trash";
    public Trash() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trash, container, false);
        tripRecyclerView = view.findViewById(R.id.tripRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        tripRecyclerView.setLayoutManager(layoutManager);
        tripRecyclerView.setHasFixedSize(true);
        RetrofitUtils retrofitUtils = new RetrofitUtils();
        retrofitUtils.getTripsUsingRetrofit("2", TRIP_STATUS,new Callback<List<TripModel>>() {
            @Override

            public void onResponse(Call<List<TripModel>> call, Response<List<TripModel>> response) {
                if (!response.isSuccessful()) {
                    //Log.e(HTTP_CODE, String.valueOf(response.code()));
                    return;
                }
                trips = response.body();
                tripAdapter = new TripRecyclerViewAdapter(trips);
                tripRecyclerView.setAdapter(tripAdapter);
            }

            @Override
            public void onFailure(Call<List<TripModel>> call, Throwable t) {
                // Log.e(RETROFIT_ERROR, t.getMessage());
            }
        });
        return view;
    }
}
