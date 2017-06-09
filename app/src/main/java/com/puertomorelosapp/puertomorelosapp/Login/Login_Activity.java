package com.puertomorelosapp.puertomorelosapp.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
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

public class Login_Activity extends AppCompatActivity implements ILogin_View {

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

    private FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login_Activity.this, Main_Activity.class));
            finish();
        }
        setContentView(R.layout.layout_login);

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
                //llamada a facebooks
            }
        });

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startRecoveryActivity();
            }
        });
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
        }
    }
}
