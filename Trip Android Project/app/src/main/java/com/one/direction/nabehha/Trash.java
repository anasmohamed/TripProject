package com.one.direction.nabehha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    RetrofitUtils retrofitUtils;
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
         retrofitUtils = new RetrofitUtils();
        retrofitUtils.getTripsUsingRetrofit(String.valueOf(AppConstants.CURRENT_USER_ID), TRIP_STATUS,new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trips=new ArrayList<>();
                if ((dataSnapshot.getValue()) != null) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Trip temp = new Trip((String) child.getKey(),
                                (String) child.child("tripName").getValue(),
                                (String) child.child("startPointAddress").getValue(),
                                (String) child.child("endPointAddress").getValue(),
                                (double) child.child("startPointLatitude").getValue(),
                                (double) child.child("startPointLongitude").getValue(),
                                (double) child.child("endPointLatitude").getValue(),
                                (double) child.child("endPointLongitude").getValue(),
                                (String) child.child("date").getValue(),
                                (String) child.child("time").getValue(),
                                TRIP_STATUS,
                                (String) child.child("type").getValue(),
                                (ArrayList<String>) child.child("notes").getValue(),
                                (String) child.child("tripImage").getValue()
                        );
                        if (!trips.contains(temp))
                            trips.add(temp);
                    }
                    tripAdapter = new TripRecyclerViewAdapter(trips,(TripRecyclerViewAdapter.CardClickedListener)getContext());
                    tripRecyclerView.setAdapter(tripAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "Failed To Log In", Toast.LENGTH_LONG).show();
            }
        });
        deleteItem();
        return view;
    }
    private void deleteItem() {
        ItemTouchHelper.SimpleCallback simpleItemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
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
                retrofitUtils.deleteTripsUsingRetrofit(String.valueOf(AppConstants.CURRENT_USER_ID), TRIP_STATUS,String.valueOf(deletedTrip.getTripId()));
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelper);
        itemTouchHelper.attachToRecyclerView(tripRecyclerView);
    }
}
