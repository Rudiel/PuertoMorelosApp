package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos.IGalleryClickListener;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Gallery_Adapter extends RecyclerView.Adapter<Gallery_Adapter.ViewHolder> {

    private List<Gallery> galleryList;
    private Context context;
    private IGalleryClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivGallery;
        private ProgressBar pbLoadingGallery;

        public ViewHolder(View itemView) {
            super(itemView);

            ivGallery = (ImageView) itemView.findViewById(R.id.ivGallery);
            pbLoadingGallery = (ProgressBar) itemView.findViewById(R.id.pbGalleryLoading);
        }
    }

    public Gallery_Adapter(List<Gallery> galleryList, Context context, IGalleryClickListener listener) {
        this.galleryList = galleryList;
        this.context = context;
        this.listener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_gallery_item, parent, false);
        return new Gallery_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Gallery_Adapter.ViewHolder holder, final int position) {

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

        holder.pbLoadingGallery.setVisibility(View.VISIBLE);

        Glide.with(context).load(galleryList.get(position).getOriginal()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.pbLoadingGallery.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.ivGallery);

        holder.ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGalleryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }


}
