package com.example.carolina.notesexcersice.addNotes.events;

/**
 * Created by carolina on 21/04/17.
 */

public class AddNotesEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
