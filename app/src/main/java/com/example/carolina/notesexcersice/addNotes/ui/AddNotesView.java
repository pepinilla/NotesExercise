package com.example.carolina.notesexcersice.addNotes.ui;

import com.example.carolina.notesexcersice.addNotes.events.AddNotesEvent;

/**
 * Created by carolina on 23/04/17.
 */

public interface AddNotesView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();
    void noteAdded(AddNotesEvent event);
    void noteNotAdded();
}
