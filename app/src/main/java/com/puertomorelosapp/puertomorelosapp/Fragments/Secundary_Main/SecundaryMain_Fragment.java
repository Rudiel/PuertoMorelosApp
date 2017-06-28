package com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Categories_Adapter;
import com.puertomorelosapp.puertomorelosapp.Adpaters.SubCategories_Adapter;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public class SecundaryMain_Fragment extends Fragment implements ISecundaryMain_view {

    @Inject
    ISecundaryMain_Presenter presenter;

    @Bind(R.id.rvSubcategories)
    RecyclerView rvSubcategories;

    @Bind(R.id.pbsubcategories)
    ProgressBar pbSubcategories;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_subcategories, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this, v);

        presenter.setView(this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvSubcategories.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        rvSubcategories.setLayoutManager(mLayoutManager);

        ((Main_Activity) getActivity()).setToolbarTitle(((Main_Activity) getActivity()).category.getName());
        ((Main_Activity) getActivity()).ivMap.setVisibility(View.GONE);

        pbSubcategories.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        ((Main_Activity) getActivity()).hideMenu();

        presenter.getSubCategories((((Main_Activity) getActivity()).category));


    }

    @Override
    public void hideLoading() {
        pbSubcategories.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        pbSubcategories.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSubCategories(List<SubCategory> subCategories) {
        mAdapter = new SubCategories_Adapter(subCategories, getActivity());

        rvSubcategories.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            rvSubcategories.setAdapter(mAdapter);
            ((Main_Activity) getActivity()).setToolbarTitle(((Main_Activity) getActivity()).category.getName());
            pbSubcategories.setVisibility(View.GONE);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
