package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jsibbold.zoomage.ZoomageView;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 9/11/17.
 */

public class Selfie_Pager_Adapter extends PagerAdapter {

    private List<Selfie> selfieList;
    private Context context;


    public Selfie_Pager_Adapter(List<Selfie> selfieList, Context context) {
        this.selfieList = selfieList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return selfieList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_dialog_gallery_item, container, false);

        ZoomageView ivGallery = (ZoomageView) view.findViewById(R.id.ivGalleryImage);

        final ProgressBar pbLoadingImage=(ProgressBar) view.findViewById(R.id.pbLoadingImage);

        pbLoadingImage.setVisibility(View.VISIBLE);

        Glide.with(context).load(selfieList.get(position).getSelfieOriginal()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                pbLoadingImage.setVisibility(View.GONE);
                return false;
            }
        }).into(ivGallery);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
