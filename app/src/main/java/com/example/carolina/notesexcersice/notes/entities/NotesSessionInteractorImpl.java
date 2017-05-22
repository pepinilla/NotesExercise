package com.example.carolina.notesexcersice.notes.entities;

/**
 * Created by carolina on 05/05/17.
 */

public class NotesSessionInteractorImpl implements NotesSessionInteractor {

    NotesRepository notesRepository;

    public NotesSessionInteractorImpl() {
        this.notesRepository = new NotesRepositoryImpl();
    }


    @Override
    public void changeConnectionStatus(boolean online) {
        notesRepository.changeUserConnectionStatus(online);
    }
}
