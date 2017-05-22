package com.example.carolina.notesexcersice.notes.entities.events;

import com.example.carolina.notesexcersice.notes.entities.Notes;

import java.util.List;

/**
 * Created by carolina on 01/05/17.
 */

public class NotesContents {

    List<Notes> notes;

    public NotesContents(List<Notes> notes) {
        this.notes = notes;
    }

    public List<Notes> getNotes(){
        return notes;
    }
}
