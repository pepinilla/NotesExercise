package com.example.carolina.notesexcersice.login.ui;

import android.support.annotation.NonNull;

import com.example.carolina.notesexcersice.contactList.entities.User;
import com.example.carolina.notesexcersice.domain.FirebaseHelper;
import com.example.carolina.notesexcersice.lib.EventBus;
import com.example.carolina.notesexcersice.lib.GreenRobotEventBus;
import com.example.carolina.notesexcersice.login.ui.events.LoginEvent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by carolina on 10/05/17.
 */

public class LoginRepositoryImpl implements LoginRespository {
    private FirebaseHelper helper;
    private DatabaseReference databaseReference;
    private DatabaseReference myUserReference;


    public LoginRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        databaseReference = helper.getDatareference();
        myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String email, final String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        postEvent(LoginEvent.onSignUpSuccess);
                        signIn(email, password);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        postEvent(LoginEvent.onSignUpError);

                    }
                });
    }

    @Override
    public void signIn(String email, String password) {
        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(email, password).
                    addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            myUserReference = helper.getMyUserReference();
                            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    initSignIn(dataSnapshot);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    postEvent(LoginEvent.onSignInError, databaseError.getMessage());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            postEvent(LoginEvent.onSignInError, e.getMessage());
                        }
                    });

        }catch (Exception e){
            postEvent(LoginEvent.onSignInError, e.getMessage());
        }

    }

    @Override
    public void checkAlreadyAuthenticated() {
        if (FirebaseAuth.getInstance().getCurrentUser()!= null){
            myUserReference = helper.getMyUserReference();
            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    initSignIn(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    postEvent(LoginEvent.onSignInError, databaseError.getMessage());
                }
            });
        }else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }

    }

    public void registerNewUser(){
        String email = helper.getAuthUserEmail();
        if (email != null){
            User currentUser = new User(email, true, null);
            myUserReference.child(FirebaseHelper.INFO_USER).setValue(currentUser);
        }

    }

    public void initSignIn (DataSnapshot dataSnapshot){
        User currentUser = dataSnapshot.getValue(User.class);
        if (currentUser == null){
            registerNewUser();
        }
        helper.changeUserConnectionStatus(User.ONLINE);
        postEvent(LoginEvent.onSignInSuccess);
    }

    public void postEvent(int type){
        postEvent(type, null);


    }

    public void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null){
            loginEvent.setErrorMesage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);

    }
}
