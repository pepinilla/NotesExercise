package com.example.carolina.notesexcersice.login.ui;

/**
 * Created by carolina on 10/05/17.
 */

public interface LoginRespository {
    void signUp(final String email, final String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticated();
}
