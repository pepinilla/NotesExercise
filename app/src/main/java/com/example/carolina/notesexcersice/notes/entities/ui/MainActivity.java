package com.example.carolina.notesexcersice.notes.entities.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.carolina.notesexcersice.R;
import com.example.carolina.notesexcersice.addNotes.ui.AddNotesFragment;
import com.example.carolina.notesexcersice.notes.entities.Notes;
import com.example.carolina.notesexcersice.notes.entities.NotesPresenter;
import com.example.carolina.notesexcersice.notes.entities.NotesPresenterImpl;
import com.example.carolina.notesexcersice.notes.entities.ui.NotesView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NotesView{

    @Bind(R.id.my_toolbar)
    Toolbar my_toolbar;


    private NotesPresenter notesPresenter;
    private NotesView notesView;

    private FragmenRecyclerView notesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        notesPresenter = new NotesPresenterImpl(this);
        notesPresenter.onCreate();

        setSupportActionBar(my_toolbar);

        showNotesList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        notesPresenter.onResume();
        notesPresenter.fetchNotes();
    }

    @Override
    protected void onPause() {
        super.onPause();
        notesPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        notesPresenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void setTitle(String title) {
        notesPresenter.setTitle(title);

    }

    @Override
    public void setContent(String content) {
        notesPresenter.setContent(content);

    }

    @Override
    public void onNotesAdded(List<Notes> notes) {
        if (notesList != null) {
            notesList.setupAdapter(notes);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_favorite){

            showDialogAddNotes();

        }
        if (id==R.id.action_settings){
            Toast.makeText(this, "Android Menu is Clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void showNotesList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        notesList = new FragmenRecyclerView();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        String tag = notesList.getClass().getSimpleName();
//        mFragmentStack.add(tag);
        transaction.add(R.id.fragment_swap, notesList,tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    private void showDialogAddNotes(){
        AddNotesFragment addNotesDialogFragment = new AddNotesFragment();
        addNotesDialogFragment.show(getSupportFragmentManager(), "addNotes");
    }

    public void showDialogEditNote(Notes note){
        AddNotesFragment addNotesDialogFragment = new AddNotesFragment(note);
        addNotesDialogFragment.show(getSupportFragmentManager(), "editNotes");
    }
}
