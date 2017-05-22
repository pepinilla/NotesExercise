package com.example.carolina.notesexcersice.notes.entities;

/**
 * Created by carolina on 05/05/17.
 */

public class NotesInteractorImpl implements NotesInteractor {
    NotesRepository notesRepository;

    public NotesInteractorImpl() {
        this.notesRepository = new NotesRepositoryImpl();
    }

    @Override
    public void setTitle(String title) {
        notesRepository.setTitle(title);
    }

    @Override
    public void setContent(String content) {
        notesRepository.setContent(content);
    }

    @Override
    public void destroyChatListener() {
        notesRepository.destroyChatListener();
    }

    @Override
    public void subscribeForUpdates() {
        notesRepository.subscribeForUpdates();
    }

    @Override
    public void unsubscribeForUpdates() {
        notesRepository.unsubscribeForUpdates();
    }

    @Override
    public void fetchNotes() {
        notesRepository.fetchNotes();
    }
}
