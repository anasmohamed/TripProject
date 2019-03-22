package com.one.direction.nabehha.ui.addtrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.one.direction.nabehha.R;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> notesList;
    private Context context;


    public NotesAdapter(ArrayList<String> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notesList.size();
    }

    @Override
    public Object getItem(int pos) {
        return notesList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.note_item, null);
        }


        TextView textViewAddNote = view.findViewById(R.id.textView_add_note);
        textViewAddNote.setText(notesList.get(position));

        ImageView imgDeleteNote = view.findViewById(R.id.delete_note);

        imgDeleteNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                notesList.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
