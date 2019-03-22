package com.one.direction.nabehha.ui.addtrip;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.direction.nabehha.R;
import com.one.direction.nabehha.SignUpActivity;
import com.one.direction.nabehha.databinding.FragmentAddNoteBinding;

import java.util.ArrayList;


public class AddNoteFragment extends Fragment {

    FragmentAddNoteBinding mAddNoteFragmentBinding;

    ArrayList<String> notesArrayList;
    NotesAdapter notesAdapter;
  //  Note note;

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAddNoteFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false);
        View view = mAddNoteFragmentBinding.getRoot();
       // note = new Note();
        notesArrayList = new ArrayList<>();
        notesArrayList.add("anas");
        notesAdapter = new NotesAdapter(notesArrayList, getActivity());

        mAddNoteFragmentBinding.listViewNotes.setAdapter(notesAdapter);
        mAddNoteFragmentBinding.imageAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mAddNoteFragmentBinding.textViewAddNote.getText().toString() != null) {
                    notesArrayList.add(mAddNoteFragmentBinding.textViewAddNote.getText().toString());
                    mAddNoteFragmentBinding.textViewAddNote.setText("");
                    notesAdapter.notifyDataSetChanged();
                }
            }
        });
        mAddNoteFragmentBinding.doneBtnAddNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle notesBundle = new Bundle();
                        notesBundle.putStringArrayList("notes", notesArrayList);
                        AddTripFragment addTripFragment = AddTripFragment.newInstance();
                        addTripFragment.setArguments(notesBundle);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, addTripFragment, "")
                                .addToBackStack("").commit();

                    }
                }
        );

        return view;

    }



}
