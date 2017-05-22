package com.example.carolina.notesexcersice.login.ui;

import com.example.carolina.notesexcersice.login.ui.events.LoginEvent;

/**
 * Created by carolina on 11/05/17.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent loginEvent);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
