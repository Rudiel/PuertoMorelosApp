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
import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 9/14/17.
 */

public class Tuturial_Activity_Creator {

    public void showTutorial(Context context) {

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
