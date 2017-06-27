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

import com.puertomorelosapp.puertomorelosapp.Adpaters.Categories_Adapter;
import com.puertomorelosapp.puertomorelosapp.Adpaters.ThirdCategories_Adapter;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.ThirdCategory;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/27/17.
 */

public class Third_Main_Fragment extends Fragment implements IThird_View {

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

        rvThirdCategories.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        rvThirdCategories.setLayoutManager(mLayoutManager);

        //if (((Main_Activity) getActivity()).categorieList.size() == 0)
        presenter.getThirdCategories(((Main_Activity) getActivity()).category);
        //else
        //  showCategories(((Main_Activity) getActivity()).categorieList);

        ((Main_Activity) getActivity()).setToolbarTitle((((Main_Activity) getActivity()).category.getName()));

        pbThirdCategories.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

    }

    @Override
    public void showThridCategories(List<ThirdCategory> thirdCategories) {
        mAdapter = new ThirdCategories_Adapter(thirdCategories, getActivity());

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
}
