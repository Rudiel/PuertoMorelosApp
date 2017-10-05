package com.puertomorelosapp.puertomorelosapp.Fragments.Categories;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Categories_Adapter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Map.Fragment_Map;
import com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main.SecundaryMain_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main.Third_Main_Fragment;
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

    @Bind(R.id.ivAd)
    ImageView ivAd;

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Main_Activity activity;


    public static int index = -1;
    public static int top = -1;

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

        activity = ((Main_Activity) getActivity());

        activity.toolbar.setVisibility(View.VISIBLE);


        mLayoutManager = new LinearLayoutManager(getActivity());

        rvCategories.setLayoutManager(mLayoutManager);

        activity.ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new Fragment_Map(), false, null);
                activity.setToolbarTitle(getString(R.string.app_name));
            }
        });


        activity.setToolbarTitle(getActivity().getString(R.string.descubre_title));

        pbCategories.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        activity.showMenu();

        activity.ivMap.setVisibility(View.VISIBLE);

        activity.ivMap.setImageDrawable(getResources().getDrawable(R.drawable.ic_pin_drop_white_36dp));

        if (activity.categorieList.size() == 0) {
            presenter.getCategories(getActivity());
        } else
            showCategories(activity.categorieList);

        activity.subCategoryList.clear();

        rvCategories.setHasFixedSize(true);


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

        activity.categorieList = categories;

        mAdapter = new Categories_Adapter(categories, getActivity(), this);

        rvCategories.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }


    @Override
    public void onClickCategory(Categorie categoria) {

        Log.d("CATE_SELECTED", categoria.getName());

        activity.mainCategory = categoria.getName();

        activity.category = categoria;

        if (categoria.getName().equals("Comercios") || categoria.getName().equals("Servicios")) {
            activity.setFragment(new Third_Main_Fragment(), true, null);

        } else {
            activity.setFragment(new SecundaryMain_Fragment(), true, null);
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        //read current recyclerview position
        index = mLayoutManager.findFirstVisibleItemPosition();
        View v = rvCategories.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - rvCategories.getPaddingTop());
    }

    @Override
    public void onResume() {
        super.onResume();

        if (index != -1) {
            mLayoutManager.scrollToPositionWithOffset(index, top);
        }

    }
}
