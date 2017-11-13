package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 2/7/17.
 */

public class Loading_Creator {


    public Dialog showLoadingLogin(Context context, String message) {
        final Dialog dialogLoading = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogLoading.setContentView(R.layout.layout_loading_login);

        dialogLoading.getWindow().getAttributes().windowAnimations = R.style.animationdialog;

        Window windowLoading = dialogLoading.getWindow();

        //windowLoading.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent)));
        windowLoading.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //windowLoading.setBackgroundDrawableResource(android.R.color.black);

        dialogLoading.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);


        dialogLoading.setCancelable(false);
        dialogLoading.setCanceledOnTouchOutside(false);

        final ProgressBar pbLoading = dialogLoading.findViewById(R.id.pbLoadingLogin);

        pbLoading.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        TextView tvMessage = dialogLoading.findViewById(R.id.tvLoadingLoginMessage);

        tvMessage.setText(message);

        return dialogLoading;
    }

}
