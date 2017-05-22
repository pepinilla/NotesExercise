package com.example.carolina.notesexcersice.login.ui;

/**
 * Created by carolina on 11/05/17.
 */

public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSingIn(String email, String password);
    void doSingUp(String email, String password);
}
