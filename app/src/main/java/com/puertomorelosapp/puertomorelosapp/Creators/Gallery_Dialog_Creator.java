package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Gallery_Pager_Adapter;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/17/17.
 */

public class Gallery_Dialog_Creator {

    public void showGallery(Context context, List<Gallery> galleryList, int position) {

        final Dialog dialogGallery = new Dialog(context);
        dialogGallery.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogGallery.setContentView(R.layout.layout_dialog_gallery);

        Window window = dialogGallery.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setBackgroundDrawableResource(android.R.color.black);

        dialogGallery.setCancelable(true);

        dialogGallery.setCanceledOnTouchOutside(false);

        dialogGallery.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);

        ViewPager viewPager = (ViewPager) dialogGallery.findViewById(R.id.vpGallery);

        final TextView tvPosition = (TextView) dialogGallery.findViewById(R.id.tvGalleryPosition);

        TextView tvTotal = (TextView) dialogGallery.findViewById(R.id.tvGalleryTotal);

        ImageButton ibClose = (ImageButton) dialogGallery.findViewById(R.id.btCloseGallery);

        tvPosition.setText(String.valueOf(position + 1));

        tvTotal.setText(String.valueOf(galleryList.size()));

        viewPager.setAdapter(new Gallery_Pager_Adapter(context, galleryList));

        viewPager.setCurrentItem(position);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPosition.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGallery.dismiss();
            }
        });

        dialogGallery.show();


    }
}
