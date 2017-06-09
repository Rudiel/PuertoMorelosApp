package com.puertomorelosapp.puertomorelosapp.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Register;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Register_Activity extends AppCompatActivity implements IRegister_View {

    @Inject
    IRegister_Presenter presenter;

    @Bind(R.id.etEmailRegister)
    EditText etEmailRegister;

    @Bind(R.id.etPasswordRegister)
    EditText etPasswordRegister;

    @Bind(R.id.etUsernameRegister)
    EditText etUsernameRegister;

    @Bind(R.id.pbRegister)
    ProgressBar pbregister;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        auth = FirebaseAuth.getInstance();

        ((PuertoMorelosApplication) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);


        Glide.with(this).load(R.drawable.login_back).dontTransform().into((ImageView) findViewById(R.id.ivBackRegister));

        (findViewById(R.id.btRegisterRegister)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });


    }

    @Override
    public void showLoading() {
        pbregister.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbregister.setVisibility(View.GONE);
    }

    @Override
    public void registerDone() {
        startActivity(new Intent(Register_Activity.this, Main_Activity.class));
        finish();
    }

    @Override
    public void showMessageError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void startRegister() {

        if (!etEmailRegister.getText().toString().isEmpty() &&
                !etPasswordRegister.getText().toString().isEmpty() &&
                !etUsernameRegister.getText().toString().isEmpty()) {

            Register register = new Register();
            register.setEmail(etEmailRegister.getText().toString());
            register.setPassword(etPasswordRegister.getText().toString());
            register.setUsername(etUsernameRegister.getText().toString());
            register.setContext(Register_Activity.this);

            presenter.registerUser(register, auth);
        }
    }
}
