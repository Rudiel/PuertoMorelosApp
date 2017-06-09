package com.puertomorelosapp.puertomorelosapp.Recover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.puertomorelosapp.puertomorelosapp.R;


/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Recover_Activity extends AppCompatActivity implements IRecover_View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recover);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
