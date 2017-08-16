package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Selfies_Adapter extends RecyclerView.Adapter<Selfies_Adapter.ViewHolder> {

    private List<Selfie> selfieList;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivSelfie;
        ImageView ivSelfieProfile;
        TextView tvSelfieDate;
        TextView tvSelfieCommet;
        TextView tvSelfieUserName;
        CardView cvSelfie;


        public ViewHolder(View itemView) {

            super(itemView);

            cvSelfie = (CardView) itemView.findViewById(R.id.cvSelfie);
            ivSelfie = (ImageView) itemView.findViewById(R.id.ivSelfie);
            ivSelfieProfile = (ImageView) itemView.findViewById(R.id.ivSelfieProfile);
            tvSelfieDate = (TextView) itemView.findViewById(R.id.tvSelfieDate);
            tvSelfieCommet = (TextView) itemView.findViewById(R.id.tvSelfieComment);
            tvSelfieUserName = (TextView) itemView.findViewById(R.id.tvSelfieNameUser);
        }
    }

    public Selfies_Adapter(List<Selfie> selfieList, Context context) {
        this.selfieList = selfieList;
        this.context = context;
    }

    @Override
    public Selfies_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_selfie_item, parent, false);
        return new Selfies_Adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final Selfies_Adapter.ViewHolder holder, int position) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        ViewGroup.LayoutParams params = holder.cvSelfie.getLayoutParams();

        try {
            params.height = displaymetrics.heightPixels / 3;
            params.width = displaymetrics.widthPixels / 2;
            holder.cvSelfie.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvSelfieCommet.setText(selfieList.get(position).getComentario());

        holder.tvSelfieDate.setText(selfieList.get(position).getFecha());

        //Se tendra que buscar el nombre del usuario por id
        //holder.tvSelfieUserName.setText(selfieList.get(position).get);

        Uri pic = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();

        if (pic == null) {
            holder.ivSelfieProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_deafult));
        } else {

            Glide.with(context).load(String.valueOf(pic)).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivSelfieProfile) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.ivSelfieProfile.setImageDrawable(circularBitmapDrawable);
                }
            });
        }


        Glide.with(context).load(selfieList.get(position).getSelfieOriginal()).centerCrop().into(holder.ivSelfie);

    }

    @Override
    public int getItemCount() {
        return selfieList.size();
    }


}
