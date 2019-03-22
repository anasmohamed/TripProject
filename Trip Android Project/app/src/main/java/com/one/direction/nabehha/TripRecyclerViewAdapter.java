package com.one.direction.nabehha;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.direction.nabehha.data.database.model.TripModel;

import java.util.ArrayList;
import java.util.List;

public class TripRecyclerViewAdapter extends RecyclerView.Adapter<TripRecyclerViewAdapter.TripViewHolder> {

    List<TripModel> trips = new ArrayList();
    TripModel recentlyDeletedTrip;
    int recentlyDeletedTripPosition;

    public TripRecyclerViewAdapter(List<TripModel> trips) {
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_card, viewGroup, false);
        TripViewHolder viewHolder = new TripViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder tripViewHolder, int position) {
        tripViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public void deleteItem(int position) {
        recentlyDeletedTrip = trips.get(position);
        recentlyDeletedTripPosition = position;
        trips.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, trips.size());
    }

    public void restoreItem(TripModel trip, int position) {
        trips.add(recentlyDeletedTripPosition, recentlyDeletedTrip);
        // notify item added by position
        notifyItemInserted(position);
    }

    class TripViewHolder extends RecyclerView.ViewHolder {
        TextView tripName;
        ImageView tripImage;
        TextView tripDate;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.trip_card_name);
            tripImage = itemView.findViewById(R.id.trip_card_image);
            tripDate = itemView.findViewById(R.id.trip_card_date);
        }
        void bind(int position) {
            tripName.setText(trips.get(position).getTripName());
            tripDate.setText(trips.get(position).getDate());
        }
    }
}
