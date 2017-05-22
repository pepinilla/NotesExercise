package com.example.carolina.notesexcersice.addNotes;

import com.example.carolina.notesexcersice.addNotes.events.AddNotesEvent;

/**
 * Created by carolina on 24/04/17.
 */

public interface AddNotesPresenter {
    void onShow();
    void onDestroy();
    void addNotes(String title, String content);
    void onEventMainThreat(AddNotesEvent event);
}
