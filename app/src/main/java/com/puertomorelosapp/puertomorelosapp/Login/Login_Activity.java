package com.puertomorelosapp.puertomorelosapp.Login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.puertomorelosapp.puertomorelosapp.Creators.Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.User;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Recover.Recover_Activity;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Activity;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Login_Activity extends AppCompatActivity implements ILogin_View, FacebookCallback<LoginResult> {

    @Inject
    ILogin_Presenter presenter;

    @Bind(R.id.btLogin)
    Button btLogin;

    @Bind(R.id.btRegistrer)
    Button btRegister;

    @Bind(R.id.btLoginFacebook)
    Button btLoginFace;

    @Bind(R.id.pbLogin)
    ProgressBar pbLogin;

    @Bind(R.id.tvForget)
    TextView tvForget;

    @Bind(R.id.etUser)
    EditText etUserEmail;

    @Bind(R.id.etPassword)
    EditText etPassword;

    @Bind(R.id.blFacebook)
    LoginButton lbFacebook;

    private FirebaseAuth auth;


    private CallbackManager callbackManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login_Activity.this, Main_Activity.class));
            finish();
        }
        setContentView(R.layout.layout_login);

        FacebookSdk.sdkInitialize(getApplicationContext());

        ((PuertoMorelosApplication) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.login_back).dontTransform().into((ImageView) findViewById(R.id.ivBackLogin));

        ((TextView) findViewById(R.id.tvTitleLogin)).setTypeface(Utils.getbukharisLetter(this));

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.starRegisterActivity();
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });

        btLoginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lbFacebook.performClick();
            }
        });

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startRecoveryActivity();
            }
        });


        callbackManager = CallbackManager.Factory.create();

        lbFacebook.setReadPermissions("email", "public_profile");
        lbFacebook.registerCallback(callbackManager, this);


    }

    @Override
    public void showLoading() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void showRegisterActivity() {
        startActivity(new Intent(Login_Activity.this, Register_Activity.class));
    }

    @Override
    public void showMainActivity() {
        startActivity(new Intent(Login_Activity.this, Main_Activity.class));
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        new Dialog_Creator().showAlertDialog(
                this,
                getString(R.string.title_register),
                message,
                new IDialog_Creator() {
                    @Override
                    public void didConfirm(Dialog dialog) {

                    }

                    @Override
                    public void didCancel(Dialog dialog) {

                    }

                    @Override
                    public void didOK(Dialog dialog) {
                        dialog.dismiss();
                    }
                }
        );
    }

    @Override
    public void showRecoveryActivity() {
        startActivity(new Intent(Login_Activity.this, Recover_Activity.class));
    }

    private void startLogin() {
        if (!etPassword.getText().toString().isEmpty() && !etUserEmail.getText().toString().isEmpty()) {
            User user = new User();
            user.setEmail(etUserEmail.getText().toString());
            user.setPassword(etPassword.getText().toString());
            user.setContext(Login_Activity.this);

            presenter.loginWithPassandUser(auth, user);
        } else
            this.showErrorMessage(checkFieldsEmpty());
    }

    private String checkFieldsEmpty() {

        if (etUserEmail.getText().toString().trim().equals(""))
            return getString(R.string.email_empty);
        else if (etPassword.getText().toString().trim().equals(""))
            return getString(R.string.password_empty);
        else return "";
    }


    @Override
    public void onSuccess(LoginResult loginResult) {
        presenter.loginWithFacebook(loginResult.getAccessToken(), auth, this);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {
        showErrorMessage(error.getMessage());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
