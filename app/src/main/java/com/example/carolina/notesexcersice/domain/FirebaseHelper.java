package com.example.carolina.notesexcersice.domain;

import android.provider.ContactsContract;

import com.example.carolina.notesexcersice.contactList.entities.User;
import com.example.carolina.notesexcersice.notes.entities.Notes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by carolina on 24/04/17.
 */

public class FirebaseHelper {

    DatabaseReference datareference;

    private final static String USER_PATH = "users";
    public final static String CONTACTS_PATH = "contacts";
    public static final String INFO_USER = "infoUser";
    private static final String MY_NOTES = "notes";

    private List<Notes> notesList = new ArrayList<Notes>();

    public static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper(){
        datareference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatareference() {
        return datareference;
    }

    public String getAuthUserEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if (user != null){
            email = user.getEmail();
        }
        return email;
    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if (email != null){
            String emailkey = email.replace(".", "_");
            userReference = datareference.getRoot().child(USER_PATH).child(emailkey);
        }
        return userReference;
    }

    public DatabaseReference getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getMyNotesReference(){
        return getMyUserReference().child(MY_NOTES);
    }

    public DatabaseReference getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public DatabaseReference getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    public DatabaseReference getOneContactReference(String mainMail, String childMail){
        String childkey = childMail.replace(".","_");
        return getUserReference(mainMail).child(CONTACTS_PATH).child(childkey);
    }

   public DatabaseReference getTitleReference(String title){
       if(title == null){
           return null;
       }
        String keyTitle = title.replace(".","_");

        String keyTitleUser = keyTitle;
        return getMyNotesReference().child(keyTitleUser);
    }

    public DatabaseReference getContentReference(String content){
        String keyContent = content.replace(".","_");

        String keyContentUser = keyContent;
        return getMyNotesReference().child(keyContentUser);
    }

    public void changeUserConnectionStatus(boolean online){
        if (getMyUserReference() != null){
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyUserReference().child(INFO_USER).updateChildren(updates);
            notifyChangeOfStatus(online);
        }
    }

    public void notifyChangeOfStatus (final boolean online, final boolean signoff){
        final String myemail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    String email = child.getKey();
                    DatabaseReference reference = getOneContactReference(email, myemail);
                    reference.setValue(online);
                }
                if (signoff)
                {
                    FirebaseAuth.getInstance().signOut();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void notifyChangeOfStatus (boolean online){
        notifyChangeOfStatus(online, false);
    }

    public void signOff(){
        notifyChangeOfStatus(User.OFFLINE, true);
    }

}
