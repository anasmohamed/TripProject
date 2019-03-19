package com.example.triprecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TripRecyclerViewAdapter extends RecyclerView.Adapter<TripRecyclerViewAdapter.TripViewHolder> {


    public TripRecyclerViewAdapter(){

    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_card, viewGroup , false);
        TripViewHolder viewHolder  = new TripViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder tripViewHolder, int position) {
        tripViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class TripViewHolder extends RecyclerView.ViewHolder{
        TextView tripName ;
        ImageView tripImage;
        TextView tripDate;
        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.trip_card_name);
            tripImage = itemView.findViewById(R.id.trip_card_image);
            tripDate = itemView.findViewById(R.id.trip_card_date);
        }

        void bind(int position){
            tripName.setText("this is trip name");
        }
    }
}
