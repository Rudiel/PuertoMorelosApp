package com.puertomorelosapp.puertomorelosapp.Recover;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Creators.Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Recovery;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Activity;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Recover_Activity extends AppCompatActivity implements IRecover_View {

    @Inject
    IRecover_Presenter presenter;

    @Bind(R.id.btSenMail)
    Button btSendMail;

    @Bind(R.id.etRecoveryMail)
    EditText etRecoveryMail;

    @Bind(R.id.btRecoveryLogin)
    Button btRecoveryLogin;

    @Bind(R.id.btRecoveryRegistrer)
    Button btRecoveryRegister;

    @Bind(R.id.pbRecovery)
    ProgressBar pbRecovery;

    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_recover);

        ((PuertoMorelosApplication) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        Glide.with(this).load(R.drawable.login_back).centerCrop().into((ImageView) findViewById(R.id.ivBackRecovery));

        ((TextView) findViewById(R.id.tvTitleRecovery)).setTypeface(Utils.getbukharisLetter(this));

        btSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoveryAccount();
            }
        });

        btRecoveryLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recover_Activity.this, Login_Activity.class));
                finish();
            }
        });

        btRecoveryRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recover_Activity.this, Register_Activity.class));
                finish();
            }
        });

        setDrawableHint(android.R.color.darker_gray);
    }

    private void recoveryAccount() {

        if (!etRecoveryMail.getText().toString().isEmpty()) {
            Recovery recovery = new Recovery();
            recovery.setContext(this);
            recovery.setEmail(etRecoveryMail.getText().toString());

            presenter.recoveryAccount(recovery, auth);
        } else
            this.showErrorMessage(checkFieldsEmpty());

    }


    @Override
    public void showLoading() {
        pbRecovery.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbRecovery.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
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

    @Override
    public void onRecoveryAccountDone() {

    }

    private String checkFieldsEmpty() {

        if (etRecoveryMail.getText().toString().trim().equals(""))
            return getString(R.string.email_empty);
        else return "";
    }

    private void setDrawableHint(int color) {
        Drawable hintEmail = this.getResources().getDrawable(R.drawable.ic_email_black_24dp);
        hintEmail.setColorFilter(ContextCompat.getColor(Recover_Activity.this, color),
                PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etRecoveryMail.setCompoundDrawablesRelativeWithIntrinsicBounds(hintEmail, null, null, null);
        }
    }
}
