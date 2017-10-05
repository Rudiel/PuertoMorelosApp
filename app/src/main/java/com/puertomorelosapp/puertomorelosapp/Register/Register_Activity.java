package com.puertomorelosapp.puertomorelosapp.Register;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Creators.Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Register;
import com.puertomorelosapp.puertomorelosapp.Models.Response.User;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

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

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_register);

        auth = FirebaseAuth.getInstance();

        ((PuertoMorelosApplication) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);


        Glide.with(this).load(R.drawable.login_back).dontTransform().into((ImageView) findViewById(R.id.ivBackRegister));

        ((TextView) findViewById(R.id.tvTitleRegister)).setTypeface(Utils.getbukharisLetter(this));

        (findViewById(R.id.btRegisterRegister)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });

        (findViewById(R.id.tvRegisterDone)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setDrawableHint(android.R.color.darker_gray);


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
    public void registerDone(User user) {

        Utils.saveUserName(this, user.getUsername());
        Utils.saveProvider(this, user.getProvider());
        Utils.saveUserEmail(this, user.getEmail());
        Utils.saveUserImage(this, user.getImageURL());

        startActivity(new Intent(Register_Activity.this, Main_Activity.class));
        finish();
    }

    @Override
    public void showMessageError(String message) {
        new Dialog_Creator().showAlertDialog(
                this,
                getString(R.string.title_register),
                message,
                new IDialog_Creator() {

                    @Override
                    public void didOK(Dialog dialog) {
                        dialog.dismiss();
                    }
                }
        );
    }

    private void startRegister() {

        if (checkFieldsEmpty().equalsIgnoreCase("")) {

            Register register = new Register();
            register.setEmail(etEmailRegister.getText().toString());
            register.setPassword(etPasswordRegister.getText().toString());
            register.setUsername(etUsernameRegister.getText().toString());
            register.setContext(Register_Activity.this);

            presenter.registerUser(register, auth);
        } else {
            this.showMessageError(checkFieldsEmpty());
        }
    }

    private String checkFieldsEmpty() {

        if (etEmailRegister.getText().toString().trim().equals(""))
            return getString(R.string.email_empty);
        else if (etPasswordRegister.getText().toString().trim().equals(""))
            return getString(R.string.password_empty);
        else if (etUsernameRegister.getText().toString().trim().equals(""))
            return getString(R.string.user_empty);

        else return "";
    }

    private void setDrawableHint(int color) {
        Drawable hintEmail = this.getResources().getDrawable(R.drawable.ic_email_black_24dp);
        hintEmail.setColorFilter(ContextCompat.getColor(Register_Activity.this, color),
                PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etEmailRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(hintEmail, null, null, null);
        }

        Drawable hintPin = this.getResources().getDrawable(R.drawable.ic_lock_black_24dp);
        hintPin.setColorFilter(ContextCompat.getColor(Register_Activity.this, color),
                PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etPasswordRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(hintPin, null, null, null);
        }

        Drawable hintUser = this.getResources().getDrawable(R.drawable.ic_person_black_24dp);
        hintUser.setColorFilter(ContextCompat.getColor(Register_Activity.this, color),
                PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etUsernameRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(hintUser, null, null, null);
        }
    }
}
