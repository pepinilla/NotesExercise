package com.example.carolina.notesexcersice.addNotes;

/**
 * Created by carolina on 26/04/17.
 */

public class AddNotesInteractorImpl implements AddNotesInteractor {
    AddNotesRepositoryImpl addNotesRepository;

    public AddNotesInteractorImpl(AddNotesRepositoryImpl addNotesRepository) {
        this.addNotesRepository = addNotesRepository;
    }

    @Override
    public void addNotes(String title, String content) {
        addNotesRepository.addNotes(title, content);

    }
}
