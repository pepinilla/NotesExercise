package com.example.carolina.notesexcersice.notes.entities.ui;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carolina.notesexcersice.R;
import com.example.carolina.notesexcersice.addNotes.ui.AddNotesFragment;
import com.example.carolina.notesexcersice.notes.entities.Notes;
import com.example.carolina.notesexcersice.notes.entities.NotesPresenter;
import com.example.carolina.notesexcersice.notes.entities.adapter.NotesAdapter;
import com.example.carolina.notesexcersice.notes.entities.adapter.OnItemClickListener;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by carolina on 07/05/17.
 */

public class FragmenRecyclerView extends Fragment implements OnItemClickListener {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private NotesAdapter adapter;
    private OnFragmentClickedListener onFragmentClickListener;

    public FragmenRecyclerView() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_recyclerview_list_of_notes, container, false);
        ButterKnife.bind(this, view);

        setupAdapter();
        setupRecyclerView();
        return view;

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            onFragmentClickListener = (OnFragmentClickedListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }



    public void setupAdapter(){
        if (adapter==null){
            adapter = new NotesAdapter();
            adapter.setOnItemClickListener(this);
        }
    }

    public void setupAdapter(List<Notes> notesList){
        setupAdapter();
        adapter.setData(notesList);
    }

    public void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(Notes notes) {
        ((MainActivity)getActivity()).showDialogEditNote(notes);

    }


}
