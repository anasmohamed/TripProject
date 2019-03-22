package com.one.direction.nabehha;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class RecyclerViewItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private TripRecyclerViewAdapter tripRecyclerViewAdapter;

    public  RecyclerViewItemTouchHelper (TripRecyclerViewAdapter tripRecyclerViewAdapter){

        //The fist parameter in super adds support for dragging the RecyclerView item up or down
        //The second parameter tells the ItemTouchHelper (that we will attach to the RecyclerView later)
        // to pass our SimpleCallback information about left and right swipes
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.tripRecyclerViewAdapter = tripRecyclerViewAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        tripRecyclerViewAdapter.deleteItem(position);
    }
}
