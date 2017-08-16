package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Gallery_Adapter extends RecyclerView.Adapter<Gallery_Adapter.ViewHolder> {

    private List<Gallery> galleryList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivGallery;

        public ViewHolder(View itemView) {
            super(itemView);

            ivGallery = (ImageView) itemView.findViewById(R.id.ivGallery);
        }
    }

    public Gallery_Adapter(List<Gallery> galleryList, Context context) {
        this.galleryList = galleryList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_gallery_item, parent, false);
        return new Gallery_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Gallery_Adapter.ViewHolder holder, int position) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        ViewGroup.LayoutParams params = holder.ivGallery.getLayoutParams();

        try {
            params.height = displaymetrics.heightPixels / 4;
            params.width = displaymetrics.widthPixels / 3;
            holder.ivGallery.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Glide.with(context).load(galleryList.get(position).getOriginal()).centerCrop().into(holder.ivGallery);
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }


}
