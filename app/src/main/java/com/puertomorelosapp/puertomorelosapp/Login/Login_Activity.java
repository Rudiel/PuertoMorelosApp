package com.puertomorelosapp.puertomorelosapp.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Login_Activity extends AppCompatActivity {

    @Bind(R.id.btLogin)
    Button btLogin;

    @Bind(R.id.btRegistrer)
    Button btRegister;

    @Bind(R.id.btLoginFacebook)
    Button btLoginFace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        ButterKnife.bind(this);


        ((TextView) findViewById(R.id.tvTitleLogin)).setTypeface(Utils.getbukharisLetter(this));


    }
}
