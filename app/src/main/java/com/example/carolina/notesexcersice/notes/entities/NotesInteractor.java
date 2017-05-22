package com.example.carolina.notesexcersice.notes.entities;

/**
 * Created by carolina on 05/05/17.
 */

public interface NotesInteractor {
    void setTitle(String title);
    void setContent(String content);

    void destroyChatListener();
    void subscribeForUpdates();
    void unsubscribeForUpdates();

    void fetchNotes();
}
