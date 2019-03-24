package com.one.direction.nabehha;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.one.direction.nabehha.data.database.model.Note;
import com.one.direction.nabehha.data.database.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.NotesViewHolder> {

    List<String> notes = new ArrayList();
    String recentlyDeletedNote;
    int recentlyDeletedNotePosition;

    public NotesRecyclerViewAdapter(List<String> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesRecyclerViewAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_card, viewGroup, false);
        NotesRecyclerViewAdapter.NotesViewHolder viewHolder = new NotesRecyclerViewAdapter.NotesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder notesViewHolder, int position) {
        notesViewHolder.bind(position);
    }


    @Override
    public int getItemCount() {
        //return notes.size();
        return notes.size();
    }

    public void deleteItem(int position) {
        recentlyDeletedNote = notes.get(position);
        recentlyDeletedNotePosition = position;
        notes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, notes.size());
    }

    public void restoreItem(Trip trip, int position) {
        notes.add(recentlyDeletedNotePosition, recentlyDeletedNote);
        // notify item added by position
        notifyItemInserted(position);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView noteTv;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTv = itemView.findViewById(R.id.note_tv);
        }

        void bind(int position) {
             noteTv.setText(notes.get(position));
//            noteTv.setText("This is a note!");
        }
    }
}
