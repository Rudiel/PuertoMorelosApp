package com.puertomorelosapp.puertomorelosapp.Login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Creators.Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.Loading_Creator;
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

    private Dialog loading;


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

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
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

        setDrawableHint(android.R.color.darker_gray);

        loading = new Loading_Creator().showLoadingLogin(this, "Iniciando sesion");


    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.dismiss();
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

    @Override
    public void saveUserData(com.puertomorelosapp.puertomorelosapp.Models.Response.User user) {
        Utils.saveUserEmail(this, user.getEmail());
        Utils.saveProvider(this, user.getProvider());
        Utils.saveUserImage(this, user.getImageURL());
        Utils.saveUserName(this, user.getUsername());

        this.showMainActivity();
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

    private void setDrawableHint(int color) {
        Drawable hintEmail = this.getResources().getDrawable(R.drawable.ic_email_black_24dp);
        hintEmail.setColorFilter(ContextCompat.getColor(Login_Activity.this, color),
                PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etUserEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(hintEmail, null, null, null);
        }

        Drawable hintPin = this.getResources().getDrawable(R.drawable.ic_lock_black_24dp);
        hintPin.setColorFilter(ContextCompat.getColor(Login_Activity.this, color),
                PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(hintPin, null, null, null);
        }
    }


}
