package com.example.carolina.notesexcersice.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.carolina.notesexcersice.R;
import com.example.carolina.notesexcersice.notes.entities.ui.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by carolina on 10/05/17.
 */

public class LoginActivity extends AppCompatActivity implements  LoginView {
    @Bind(R.id.editTxtPassword)
    EditText editTextPassword;
    @Bind(R.id.editTxtEmail)
    EditText editTextEmail;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout layout;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();
    }
    @Override
    public void onDestroy(){
        loginPresenter.onDestroy();
        super.onDestroy();
    }



    @Override
    public void enableInputs() {
        setInputs(true);

    }

    @Override
    public void disableInputs() {
        setInputs(false);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    @OnClick(R.id.btnSignup)
    public void handleSignUp() {
        loginPresenter.registerNewUser(editTextEmail.getText().toString(),
                editTextPassword.getText().toString());

    }

    @Override
    public void handleSignIn() {
        loginPresenter.validateLogin(editTextEmail.getText().toString(),
                editTextPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginError(String error) {
        editTextPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        editTextPassword.setError(msgError);
    }

    @Override
    public void newUserSucess() {
        Snackbar.make(layout, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        editTextPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        editTextPassword.setText(msgError);

    }

    private void setInputs(boolean enabled){
        btnSignin.setEnabled(true);
        btnSignup.setEnabled(true);
        editTextEmail.setEnabled(true);
        editTextPassword.setEnabled(true);


    }


}
