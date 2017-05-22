package com.example.carolina.notesexcersice.notes.entities;


import com.example.carolina.notesexcersice.domain.FirebaseHelper;
import com.example.carolina.notesexcersice.lib.EventBus;
import com.example.carolina.notesexcersice.lib.GreenRobotEventBus;
import com.example.carolina.notesexcersice.notes.entities.events.NotesContents;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carolina on 04/05/17.
 */

public class NotesRepositoryImpl implements NotesRepository{

    private FirebaseHelper helper;
    private DatabaseReference notesDatabaseReference;
    private ChildEventListener childEventListener;
    private String title;
    private String content;

    public NotesRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        notesDatabaseReference = helper.getMyNotesReference();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public void subscribeForUpdates() {
        if (childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Notes notes = dataSnapshot.getValue(Notes.class);
                    String title = notes.getTitle();
                    title = title.replace("_", ".");

                    String currentUserEmail = helper.getAuthUserEmail();
                    notes.setCreatebyMe(notes.equals(currentUserEmail));

                    /*if(title != null && content != null) {
                        helper.getTitleReference(title).addChildEventListener(childEventListener);
                        helper.getContentReference(content).addChildEventListener(childEventListener);
                    }*/

//                    NotesContents notesContents = new NotesContents(notes);
//                    EventBus eventBus = GreenRobotEventBus.getInstance();
//                    eventBus.post(notesContents);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {                }

                @Override
                public void onCancelled(DatabaseError databaseError) {               }
            };

        }
    }



    @Override
    public void destroyChatListener() {
        childEventListener = null;
    }


    @Override
    public void unsubscribeForUpdates() {
        if (childEventListener != null){
//            helper.getTitleReference(title).removeEventListener(childEventListener);
//            helper.getContentReference(content).removeEventListener(childEventListener);
        }
    }

    @Override
    public void changeUserConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }


    @Override
    public void fetchNotes() {
        notesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Notes> notesList = new ArrayList<Notes>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Notes note = data.getValue(Notes.class);
                    notesList.add(note);
                }

                NotesContents contents = new NotesContents(notesList);
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(contents);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

