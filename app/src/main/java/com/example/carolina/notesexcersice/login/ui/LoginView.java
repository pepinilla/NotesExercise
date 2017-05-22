package com.example.carolina.notesexcersice.login.ui;

/**
 * Created by carolina on 10/05/17.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSucess();
    void newUserError(String error);
}
