package com.example.carolina.notesexcersice.addNotes;

import com.example.carolina.notesexcersice.addNotes.events.AddNotesEvent;
import com.example.carolina.notesexcersice.addNotes.ui.AddNotesView;
import com.example.carolina.notesexcersice.lib.EventBus;
import com.example.carolina.notesexcersice.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by carolina on 24/04/17.
 */

public class AddNotesPresenterImpl implements AddNotesPresenter {
    EventBus eventBus;
    AddNotesView addNotesView;
    AddNotesInteractor addNotesInteractor;

    public AddNotesPresenterImpl(AddNotesView addNotesView) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.addNotesView = addNotesView;
        this.addNotesInteractor = new AddNotesInteractorImpl(new AddNotesRepositoryImpl());
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        addNotesView = null;
        eventBus.unregister(this);
    }

    @Override
    public void addNotes(String title, String content) {
        addNotesView.hideInput();
        addNotesView.showProgress();
        this.addNotesInteractor.addNotes(title, content);
    }

    @Subscribe
    @Override
    public void onEventMainThreat(AddNotesEvent event) {
        if (addNotesView != null){
            addNotesView.hideProgress();
            addNotesView.showInput();

            if (event.isError()){
                addNotesView.noteNotAdded();}
                else{
                    addNotesView.noteAdded(event);

            }
        }
    }
}
