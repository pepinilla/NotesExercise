package com.example.carolina.notesexcersice.notes.entities;

/**
 * Created by carolina on 04/05/17.
 */

public interface NotesRepository {
    void setTitle(String title);
    void setContent(String content);

    void destroyChatListener();
    void subscribeForUpdates();
    void unsubscribeForUpdates();

    void changeUserConnectionStatus(boolean online);

    void fetchNotes();
}
