package com.example.carolina.notesexcersice.login.ui;

/**
 * Created by carolina on 11/05/17.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private LoginRespository loginRespository;

    public LoginInteractorImpl() {
        this.loginRespository = new LoginRepositoryImpl();
    }

    @Override
    public void checkAlreadyAuthenticated() {
        loginRespository.checkAlreadyAuthenticated();

    }

    @Override
    public void doSingIn(String email, String password) {
        loginRespository.signIn(email, password);

    }

    @Override
    public void doSingUp(String email, String password) {
        loginRespository.signUp(email, password);

    }
}
