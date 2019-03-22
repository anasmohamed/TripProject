package com.one.direction.nabehha;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.direction.nabehha.data.database.model.NoteModel;
import com.one.direction.nabehha.data.database.model.TripModel;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.NotesViewHolder> {

    List<NoteModel> notes = new ArrayList();
    NoteModel recentlyDeletedNote;
    int recentlyDeletedNotePosition;

//    public NotesRecyclerViewAdapter(List<NoteModel> notes) {
//        this.notes = notes;
//    }

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
        return 6;
    }

    public void deleteItem(int position) {
        recentlyDeletedNote = notes.get(position);
        recentlyDeletedNotePosition = position;
        notes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, notes.size());
    }

    public void restoreItem(TripModel trip, int position) {
        notes.add(recentlyDeletedNotePosition, recentlyDeletedNote);
        // notify item added by position
        notifyItemInserted(position);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView noteTv;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
             noteTv= itemView.findViewById(R.id.note_tv);
        }
        void bind(int position) {
           // noteTv.setText(notes.get(position).getNote());
            noteTv.setText("This is a note!");
        }
    }
}
