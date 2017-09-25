package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Activities_Selfies_Adapter;
import com.puertomorelosapp.puertomorelosapp.Creators.Gallery_Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Selfies_Fragment extends Fragment implements ISelfies_View, ISelfieClickListener_Activity {

    @Inject
    ISelfies_Presenter presenter;

    @Bind(R.id.pbActivitiesSelfies)
    ProgressBar pbActivitiesSelfies;

    @Bind(R.id.rvActivitiesSelfies)
    RecyclerView rvActivitiesSelfies;

    private Activities_Selfies_Adapter adapter;

    private List<Selfie> selfieList;

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

        selfieList = new ArrayList<>();

        rvActivitiesSelfies.setHasFixedSize(true);
        rvActivitiesSelfies.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        presenter.getSelfies(getActivity());

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

        //ordenamos las selfies por fecha
        this.selfieList = selfies;

        Collections.sort(this.selfieList, new Comparator<Selfie>() {
            @Override
            public int compare(Selfie o1, Selfie o2) {
                return o2.getFecha().compareTo(o1.getFecha());
            }
        });

        adapter = new Activities_Selfies_Adapter(getActivity(), this.selfieList, this);
        rvActivitiesSelfies.setAdapter(adapter);

    }

    @Override
    public void onSelfieClickListener(int position) {
        new Gallery_Dialog_Creator().showSelfies(getActivity(), this.selfieList, position, true, new IDeleteSelfie() {
            @Override
            public void onDeleteSelfieListener(Selfie selfie) {
                //eliminamos la selfie  mostramos un loading de eliminando
                presenter.deleteSelfie(selfie, getActivity());
            }
        });
    }
}
