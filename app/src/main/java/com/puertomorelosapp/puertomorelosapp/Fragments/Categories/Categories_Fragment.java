package com.puertomorelosapp.puertomorelosapp.Fragments.Categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Categories_Adapter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main.SecundaryMain_Fragment;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/9/17.
 */

public class Categories_Fragment extends Fragment implements ICategories_View, IOnclickCategories {

    @Inject
    ICategories_Presenter presenter;

    @Bind(R.id.rvCategories)
    RecyclerView rvCategories;

    @Bind(R.id.pbCategories)
    ProgressBar pbCategories;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_categories, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this, view);

        presenter.setView(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((Main_Activity) getActivity()).ivMap.setVisibility(View.VISIBLE);

        rvCategories.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        rvCategories.setLayoutManager(mLayoutManager);

        if (((Main_Activity) getActivity()).categorieList.size() == 0)
            presenter.getCategories(getActivity());
        else
            showCategories(((Main_Activity) getActivity()).categorieList);


    }

    @Override
    public void showLoading() {
        pbCategories.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbCategories.setVisibility(View.GONE);
    }

    @Override
    public void showCategories(List<Categorie> categories) {
        mAdapter = new Categories_Adapter(categories, getActivity(), this);

        rvCategories.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }


    @Override
    public void onClickCategory(Categorie categoria) {

        Log.d("CATE_SELECTED", categoria.getName());

        ((Main_Activity) getActivity()).category = categoria;

        ((Main_Activity) getActivity()).setFragment(new SecundaryMain_Fragment(), true);

    }
}
