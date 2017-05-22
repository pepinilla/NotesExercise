package com.example.carolina.notesexcersice.notes.entities;

import com.example.carolina.notesexcersice.contactList.entities.User;
import com.example.carolina.notesexcersice.lib.EventBus;
import com.example.carolina.notesexcersice.lib.GreenRobotEventBus;
import com.example.carolina.notesexcersice.notes.entities.events.NotesContents;
import com.example.carolina.notesexcersice.notes.entities.ui.NotesView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by carolina on 05/05/17.
 */

public class NotesPresenterImpl implements NotesPresenter {

    NotesView notesView;
    EventBus eventBus;
    NotesInteractor notesInteractor;
    NotesSessionInteractor notesSessionInteractor;

    public NotesPresenterImpl(NotesView notesView) {
        this.notesView = notesView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.notesInteractor = new NotesInteractorImpl();
        this.notesSessionInteractor = new NotesSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        notesInteractor.subscribeForUpdates();
        notesSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onPause() {
        notesInteractor.unsubscribeForUpdates();
        notesSessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        notesInteractor.destroyChatListener();
        notesView = null;
    }

    @Override
    public void setTitle(String title) {
        this.notesInteractor.setTitle(title);

    }

    @Override
    public void setContent(String content) {
        this.notesInteractor.setContent(content);

    }

    @Subscribe
    @Override
    public void onEventMainThread(NotesContents event) {
        if (notesView != null){
            List<Notes> notes = event.getNotes();
            notesView.onNotesAdded(notes);
        }

    }

    @Override
    public void fetchNotes() {
        this.notesInteractor.fetchNotes();
    }
}
