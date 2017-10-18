package com.puertomorelosapp.puertomorelosapp.TermsAndConditions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 18/10/17.
 */

public class Conditions_Activity extends AppCompatActivity {

    @Bind(R.id.pdfView)
    PDFView pdfView;

    @Bind(R.id.tbConditions)
    Toolbar tbConditioins;

    private ImageView btClose;
    private TextView tvTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_conditions);

        ButterKnife.bind(this);

        pdfView.fromAsset("terminosycondiciones.pdf").load();


        tbConditioins.setVisibility(View.VISIBLE);

        setSupportActionBar(tbConditioins);

        btClose= tbConditioins.findViewById(R.id.ivMap);

        tvTitle= tbConditioins.findViewById(R.id.tvTitleToolbar);

        tvTitle.setText(getString(R.string.menu_conditions));

        tvTitle.setTypeface(Utils.getbukharisLetter(this));

        btClose.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_white_24dp));


        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
