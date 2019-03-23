package com.one.direction.nabehha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.direction.nabehha.data.database.model.Trip;
import com.one.direction.nabehha.webServiceUtils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Trash extends Fragment {

    RecyclerView tripRecyclerView;
    TripRecyclerViewAdapter tripAdapter;
    List<Trip> trips = null;
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
        retrofitUtils.getTripsUsingRetrofit(AppConstants.CURRENT_USER_ID, TRIP_STATUS,new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trips=new ArrayList<>();
                if ((dataSnapshot.getValue()) != null) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Trip temp = new Trip((String) child.child("tripId").getKey(),
                                (String) child.child("tripName").getValue(),
                                (String) child.child("startPointAddress").getValue(),
                                (String) child.child("endPointAddress").getValue(),
                                (Long) child.child("startPointLatitude").getValue(),
                                (Long) child.child("startPointLongitude").getValue(),
                                (Long) child.child("endPointLatitude").getValue(),
                                (Long) child.child("endPointLongitude").getValue(),
                                (String) child.child("date").getValue(),
                                (String) child.child("time").getValue(),
                                TRIP_STATUS,
                                (String) child.child("type").getValue()
                        );
                        if (!trips.contains(temp))
                            trips.add(temp);
                    }
                    tripAdapter = new TripRecyclerViewAdapter(trips);
                    tripRecyclerView.setAdapter(tripAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
//                Toast.makeText(mContext, "Faild To Log In", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

}
