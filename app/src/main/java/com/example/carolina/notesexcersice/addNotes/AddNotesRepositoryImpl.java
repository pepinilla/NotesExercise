package com.example.carolina.notesexcersice.addNotes;

import com.example.carolina.notesexcersice.addNotes.events.AddNotesEvent;
import com.example.carolina.notesexcersice.contactList.entities.User;
import com.example.carolina.notesexcersice.domain.FirebaseHelper;
import com.example.carolina.notesexcersice.lib.EventBus;
import com.example.carolina.notesexcersice.lib.GreenRobotEventBus;
import com.example.carolina.notesexcersice.notes.entities.Notes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;



/**
 * Created by carolina on 26/04/17.
 */

public class AddNotesRepositoryImpl implements AddNotesRepository {
    @Override
    public void addNotes(final String title, final String content) {
        final String keytitle = title.replace(".","_");
        final String keyContent = content.replace(".","_");


        FirebaseHelper helper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = helper.getMyUserReference();
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                AddNotesEvent addNotesEvent = new AddNotesEvent();
                if (user != null){
                    FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();

                    DatabaseReference getTitleReference = firebaseHelper.getTitleReference(title);
                    getTitleReference.child("title").setValue(title);

//                    DatabaseReference getContentReference = firebaseHelper.getContentReference(content);
                    getTitleReference.child("content").setValue(content);
                }
                else
                {
                    addNotesEvent.setError(true);
                }
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(addNotesEvent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
