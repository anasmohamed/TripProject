package com.one.direction.nabehha.service.note;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.one.direction.nabehha.R;

import java.util.ArrayList;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder> {

    private ArrayList<String> notes;


    public NoteRecyclerViewAdapter(ArrayList<String> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup , false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder tripViewHolder, int position) {
        tripViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        CheckBox noteCheckBox;
        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteCheckBox = itemView.findViewById(R.id.note_check_box);
        }

        void bind(int position){
            noteCheckBox.setText(notes.get(position));
        }
    }
}
