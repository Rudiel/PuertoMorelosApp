package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Activities_Tutorial_Adapter;
import com.puertomorelosapp.puertomorelosapp.Models.Tutorial;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 9/14/17.
 */

public class Tuturial_Activity_Creator {

    public void showTutorial(final Context context, List<Tutorial> tutorialList) {

        final Dialog dialogTutorial = new Dialog(context);
        dialogTutorial.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTutorial.setContentView(R.layout.layout_activities_tutorial);

        final Window window = dialogTutorial.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //window.setBackgroundDrawableResource(android.R.color.black);
        window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.primary_transparent)));


        dialogTutorial.setCancelable(true);

        dialogTutorial.setCanceledOnTouchOutside(false);

        dialogTutorial.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);

        ViewPager viewPager = (ViewPager) dialogTutorial.findViewById(R.id.vpTutorial);

        viewPager.setAdapter(new Activities_Tutorial_Adapter(context, tutorialList));


        ImageButton ibClose = (ImageButton) dialogTutorial.findViewById(R.id.ibCloseTutorial);

        viewPager.setCurrentItem(0);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.primary_transparent)));
                        break;
                    case 1:
                        window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.purple_transparent)));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTutorial.dismiss();
            }
        });

        dialogTutorial.show();

    }
}
