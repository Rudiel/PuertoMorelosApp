package com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/12/17.
 */

public class AboutUs_Fragment extends Fragment implements IAboutUs_View {

    @Inject
    IAboutUs_Presenter presenter;

    @Bind(R.id.btAboutContact)
    Button btContact;

    @Bind(R.id.btAboutFace)
    Button btFace;

    @Bind(R.id.btAboutInsta)
    Button btInsta;

    @Bind(R.id.btAboutTwi)
    Button btTwit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_aboutus, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this, view);
        presenter.setView(this);

        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onInstagramClick();
            }
        });

        btContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onContactClick();
            }
        });

        btFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFacebookClick();
            }
        });

        btTwit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onTwitterClick();
            }
        });
    }

    @Override
    public void showFacebook() {

    }

    @Override
    public void showTwitter() {

    }

    @Override
    public void showInstagram() {

    }

    @Override
    public void showContact() {

    }
}
