package com.one.direction.nabehha;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.direction.nabehha.data.database.model.Trip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TripRecyclerViewAdapter extends RecyclerView.Adapter<TripRecyclerViewAdapter.TripViewHolder> {
    List<Trip> trips = new ArrayList();
    Trip recentlyDeletedTrip;
    int recentlyDeletedTripPosition;
    CardClickedListener cardClickedListener;
    Context context;

    public interface CardClickedListener {
        void onCardClicked(Trip trip);
    }

    public TripRecyclerViewAdapter(List<Trip> trips, CardClickedListener cardClickedListener) {
        this.trips = trips;
        this.cardClickedListener = cardClickedListener;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
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

    public void restoreItem(Trip trip, int position) {
        trips.add(recentlyDeletedTripPosition, recentlyDeletedTrip);
        // notify item added by position
        notifyItemInserted(position);
    }

    class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tripName;
        ImageView tripImage;
        TextView tripDate;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.trip_card_name);
            tripImage = itemView.findViewById(R.id.trip_card_image);
            tripDate = itemView.findViewById(R.id.trip_card_date);
            itemView.setOnClickListener(this);
        }

        void bind(final int position) {
            tripName.setText(trips.get(position).getTripName());
            tripDate.setText(trips.get(position).getDate());
            byte[] imageByte = null;
            imageByte = trips.get(position).getTripImagebyte();
            if(imageByte!=null)
            tripImage.setImageBitmap(BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length));
        }

        @Override
        public void onClick(View v) {
            cardClickedListener.onCardClicked(trips.get(getAdapterPosition()));
        }
    }
}
