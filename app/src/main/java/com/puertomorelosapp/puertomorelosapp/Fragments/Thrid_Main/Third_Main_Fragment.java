package com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main;

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

import com.puertomorelosapp.puertomorelosapp.Adpaters.ThirdCategories_Adapter;
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
 * Created by rudielavilaperaza on 6/27/17.
 */

public class Third_Main_Fragment extends Fragment implements IThird_View, IOnclikThirdListener {

    @Inject
    IThird_Main_Presenter presenter;

    @Bind(R.id.pbThirdCategories)
    ProgressBar pbThirdCategories;

    @Bind(R.id.rvThirdCategories)
    RecyclerView rvThirdCategories;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_thirdcategories, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this, view);

        presenter.setView(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //presenter.getThirdCategories(((Main_Activity) getActivity()).category);
        ((Main_Activity) getActivity()).toolbar.setVisibility(View.VISIBLE);

        rvThirdCategories.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        rvThirdCategories.setLayoutManager(mLayoutManager);

        presenter.getThirdCategories(((Main_Activity) getActivity()).category);

        if (((Main_Activity) getActivity()).category.getCategoria() != null)
            ((Main_Activity) getActivity()).setToolbarTitle((((Main_Activity) getActivity()).category.getCategoria()));
        else
            ((Main_Activity) getActivity()).setToolbarTitle((((Main_Activity) getActivity()).category.getName()));


        pbThirdCategories.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        ((Main_Activity) getActivity()).ivMap.setVisibility(View.GONE);

        ((Main_Activity) getActivity()).hideMenu();

    }

    @Override
    public void showThridCategories(List<Categorie> thirdCategories) {

        ((Main_Activity) getActivity()).thirdCategoryList = thirdCategories;

        mAdapter = new ThirdCategories_Adapter(thirdCategories, getActivity(), this);

        rvThirdCategories.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        pbThirdCategories.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbThirdCategories.setVisibility(View.GONE);
    }

    @Override
    public void onClickThirdListener(Categorie category) {
        ((Main_Activity)getActivity()).subCategoryList.clear();
        ((Main_Activity) getActivity()).category = category;
        ((Main_Activity) getActivity()).setFragment(new SecundaryMain_Fragment(), true,null);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            rvThirdCategories.setAdapter(mAdapter);
            if (((Main_Activity) getActivity()).category.getCategoria() != null)
                ((Main_Activity) getActivity()).setToolbarTitle((((Main_Activity) getActivity()).category.getCategoria()));
            else
                ((Main_Activity) getActivity()).setToolbarTitle((((Main_Activity) getActivity()).category.getName()));
            pbThirdCategories.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
