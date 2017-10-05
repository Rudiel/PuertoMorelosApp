package com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Adpaters.SubCategories_Adapter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Detail_Fragment;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
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
 * Created by rudielavilaperaza on 6/24/17.
 */

public class SecundaryMain_Fragment extends Fragment implements ISecundaryMain_view, ISecundaryOnclick {

    @Inject
    ISecundaryMain_Presenter presenter;

    @Bind(R.id.rvSubcategories)
    RecyclerView rvSubcategories;

    @Bind(R.id.pbsubcategories)
    ProgressBar pbSubcategories;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Main_Activity activity;

    @Bind(R.id.ivSecundaryCategoryBackground)
    ImageView ivSecundaryCategoryBackground;

    @Bind(R.id.ivAdSubcategory)
    ImageView ivAdSubcategory;

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

        activity = ((Main_Activity) getActivity());

        presenter.getAd(activity.category);

        activity.toolbar.setVisibility(View.VISIBLE);

        rvSubcategories.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        rvSubcategories.setLayoutManager(mLayoutManager);

        activity.setToolbarTitle(((Main_Activity) getActivity()).category.getName());
        activity.ivMap.setVisibility(View.GONE);

        pbSubcategories.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        ((Main_Activity) getActivity()).hideMenu();

        if (activity.subCategoryList.size() > 0) {
            this.showSubCategories(activity.subCategoryList);
        } else
            presenter.getSubCategories(activity.category);

        Glide.with(getActivity()).load(R.drawable.historia_fondo).into(ivSecundaryCategoryBackground);


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

        if (activity != null)
            activity.subCategoryList = subCategories;

        List<SubCategory> list = new ArrayList<>();

        for (SubCategory sub : subCategories) {
            if (sub.getActivo() != 0) {
                list.add(sub);
            }
        }

        hideLoading();

        Collections.sort(list, new Comparator<SubCategory>() {
            @Override
            public int compare(SubCategory o1, SubCategory o2) {
                return o1.getPrioridad() - o2.getPrioridad();
            }
        });

        mAdapter = new SubCategories_Adapter(list, getActivity(), this);

        rvSubcategories.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateAdapter() {

        //mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showAd(String url) {

        activity.toolbar.setVisibility(View.GONE);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ivAdSubcategory.setLayoutParams(params);

        ivAdSubcategory.setVisibility(View.VISIBLE);

        Glide.with(getActivity()).load(url).centerCrop().into(ivAdSubcategory);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.toolbar.setVisibility(View.VISIBLE);
                ivAdSubcategory.setVisibility(View.GONE);
            }
        }, 3000);
    }


    @Override
    public void onResume() {
        super.onResume();
        try {

            //rvSubcategories.setAdapter(mAdapter);
            ((Main_Activity) getActivity()).setToolbarTitle(((Main_Activity) getActivity()).category.getName());
            //pbSubcategories.setVisibility(View.GONE);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClickCategory(SubCategory category, ImageView image) {
        ((Main_Activity) getActivity()).setFragment(new Detail_Fragment(), true, image);
        ((Main_Activity) getActivity()).subCategory = category;
    }
}
