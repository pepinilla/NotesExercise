package com.example.carolina.notesexcersice.notes.entities.ui;

import com.example.carolina.notesexcersice.notes.entities.Notes;
import com.example.carolina.notesexcersice.notes.entities.events.NotesContents;

import java.util.List;

/**
 * Created by carolina on 05/05/17.
 */

public interface NotesView {
    void setTitle(String title);
    void setContent(String content);
    void onNotesAdded(List<Notes> notesList);
}
