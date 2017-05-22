package com.example.carolina.notesexcersice.notes.entities;

import com.example.carolina.notesexcersice.notes.entities.events.NotesContents;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by carolina on 05/05/17.
 */

public interface NotesPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();
    void setTitle(String title);
    void setContent(String content);

    void onEventMainThread(NotesContents event);

    void fetchNotes();

}
