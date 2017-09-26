package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Models.Tutorial;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 9/26/17.
 */

public class Activities_Tutorial_Adapter extends PagerAdapter {

    private Context context;
    private List<Tutorial> tutorialList;

    public Activities_Tutorial_Adapter(Context context, List<Tutorial> tutorialList) {
        this.context = context;
        this.tutorialList = tutorialList;
    }

    @Override
    public int getCount() {
        return tutorialList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_activities_tutorial_item, container, false);
        //setear textos
        TextView tvTitulo = (TextView) view.findViewById(R.id.tvTutorialTitle);
        TextView tvSubtitle = (TextView) view.findViewById(R.id.tvTutorialSubtitle);
        ImageView ivTutorial = (ImageView) view.findViewById(R.id.ivTutorialImage);

        tvTitulo.setText(tutorialList.get(position).getTitle());
        tvSubtitle.setText(tutorialList.get(position).getSubtitle());
        ivTutorial.setImageDrawable(tutorialList.get(position).getImage());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
