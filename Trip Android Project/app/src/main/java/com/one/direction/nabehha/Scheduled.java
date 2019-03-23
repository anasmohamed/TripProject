package com.one.direction.nabehha;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.webServiceUtils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Scheduled extends Fragment {

    RecyclerView tripRecyclerView;
    TripRecyclerViewAdapter tripAdapter;
    List<Trip> trips = null;
    RetrofitUtils retrofitUtils ;
    private static final String TRIP_STATUS = "scheduled";

    public Scheduled() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scheduled, container, false);
        tripRecyclerView = view.findViewById(R.id.tripRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        tripRecyclerView.setLayoutManager(layoutManager);
        tripRecyclerView.setHasFixedSize(true);
        retrofitUtils = new RetrofitUtils();
        //get user Id from shared preferences
        retrofitUtils.getTripsUsingRetrofit("2", TRIP_STATUS, new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (!response.isSuccessful()) {
                    //Log.e(HTTP_CODE, String.valueOf(response.code()));
                    return;
                }
                trips = response.body();
                tripAdapter = new TripRecyclerViewAdapter(trips, (TripRecyclerViewAdapter.CardClickedListener) getContext());
                tripRecyclerView.setAdapter(tripAdapter);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                // Log.e(RETROFIT_ERROR, t.getMessage());
            }
        });
        deleteItem();
        return view;
    }

    private void deleteItem() {
        ItemTouchHelper.SimpleCallback simpleItemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                final Trip deletedTrip = trips.get(position);
                final int deletedPosition = position;
                tripAdapter.deleteItem(deletedPosition);
                retrofitUtils.deleteTripsUsingRetrofit(String.valueOf(deletedTrip.getUserId()), new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Log.i("RetrofitResponse",String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.i("RetrofitResponse",String.valueOf(t.getMessage()));
                    }
                });

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelper);
        itemTouchHelper.attachToRecyclerView(tripRecyclerView);
    }

}
