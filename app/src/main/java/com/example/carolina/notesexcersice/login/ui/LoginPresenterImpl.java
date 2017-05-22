package com.example.carolina.notesexcersice.login.ui;

import com.example.carolina.notesexcersice.lib.EventBus;
import com.example.carolina.notesexcersice.lib.GreenRobotEventBus;
import com.example.carolina.notesexcersice.login.ui.events.LoginEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by carolina on 12/05/17.
 */

public class LoginPresenterImpl implements LoginPresenter {
    EventBus eventBus;
    LoginInteractor loginInteractor;
    LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.loginInteractor = new LoginInteractorImpl();
        this.loginView = loginView;
    }


    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);

    }

    @Override
    public void checkForAuthenticatedUser() {
        if (loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkAlreadyAuthenticated();

    }

    @Subscribe
    @Override
    public void onEventMainThread(LoginEvent loginEvent) {
        switch (loginEvent.getEventType()){
            case LoginEvent.onSignInError:
                onSignInError(loginEvent.getErrorMesage());
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(loginEvent.getErrorMesage());
                break;
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSucess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;

        }

    }

    public void onSignInError(String error){
        if (loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }
    public void onSignUpError(String error){
        if (loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    public void onFailedToRecoverSession(){
        if(loginView!= null){
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }

    public void onSignInSuccess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    public void onSignUpSucess(){
        if(loginView != null){
            loginView.newUserSucess();
        }

    }



    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSingIn(email, password);

    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSingUp(email, password);

    }
}
