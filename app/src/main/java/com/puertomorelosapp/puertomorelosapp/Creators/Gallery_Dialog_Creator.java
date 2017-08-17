package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Gallery_Pager_Adapter;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/17/17.
 */

public class Gallery_Dialog_Creator {

    public void showGallery(Context context, List<Gallery> galleryList, int position) {

        Dialog dialogGallery = new Dialog(context);
        dialogGallery.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogGallery.setContentView(R.layout.layout_dialog_gallery);

        Window window = dialogGallery.getWindow();

        dialogGallery.setCancelable(true);

        dialogGallery.setCanceledOnTouchOutside(false);

        dialogGallery.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);

        ViewPager viewPager = (ViewPager) dialogGallery.findViewById(R.id.vpGallery);

        viewPager.setAdapter(new Gallery_Pager_Adapter(context, galleryList));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dialogGallery.show();


    }
}
