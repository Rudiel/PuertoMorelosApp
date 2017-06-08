package com.puertomorelosapp.puertomorelosapp.Register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Register_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        Glide.with(this).load(R.drawable.login_back).dontTransform().into((ImageView) findViewById(R.id.ivBackRegister));

    }
}
