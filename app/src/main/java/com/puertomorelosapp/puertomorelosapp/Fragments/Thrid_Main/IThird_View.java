package com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main;

import com.puertomorelosapp.puertomorelosapp.Models.ThirdCategory;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/27/17.
 */

public interface IThird_View {

    void showThridCategories(List<ThirdCategory> thirdCategoryList);

    void showLoading();

    void hideLoading();

}
