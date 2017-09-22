package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Selfies_Fragment extends Fragment implements ISelfies_View {

    @Inject
    ISelfies_Presenter presenter;

    @Bind(R.id.pbActivitiesSelfies)
    ProgressBar pbActivitiesSelfies;

    @Bind(R.id.rvActivitiesSelfies)
    RecyclerView rvActivitiesSelfies;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_activities_selfies, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showLoading() {
        pbActivitiesSelfies.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbActivitiesSelfies.setVisibility(View.GONE);
    }

    @Override
    public void setSelfies(List<Selfie> selfies) {

    }
}
