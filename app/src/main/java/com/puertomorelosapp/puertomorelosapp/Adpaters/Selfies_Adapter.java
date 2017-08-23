package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos.ISelfieClickListener;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Selfies_Adapter extends RecyclerView.Adapter<Selfies_Adapter.ViewHolder> {

    private List<Selfie> selfieList;
    private Context context;
    private ISelfieClickListener listener;


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

    public Selfies_Adapter(List<Selfie> selfieList, Context context, ISelfieClickListener listener) {
        this.selfieList = selfieList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public Selfies_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_selfie_item, parent, false);
        return new Selfies_Adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final Selfies_Adapter.ViewHolder holder, final int position) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        ViewGroup.LayoutParams params = holder.cvSelfie.getLayoutParams();

        try {
            params.height = displaymetrics.heightPixels / 3 + 40;
            params.width = displaymetrics.widthPixels / 2;
            holder.cvSelfie.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvSelfieCommet.setText(selfieList.get(position).getComentario());

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM HH:mm");
        try {
            Date oneWayTripDate = input.parse(selfieList.get(position).getFecha());                 // parse input
            holder.tvSelfieDate.setText(output.format(oneWayTripDate));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Se tendra que buscar el nombre del usuario por id
        //holder.tvSelfieUserName.setText(selfieList.get(position).get);

        if (selfieList.get(position).getPhotographer().getImageURL().equals("SomeimageURL")) {
            holder.ivSelfieProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_deafult));
        } else {

            Glide.with(context).load(String.valueOf(selfieList.get(position).getPhotographer().getImageURL())).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivSelfieProfile) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.ivSelfieProfile.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

        holder.tvSelfieUserName.setText(selfieList.get(position).getPhotographer().getUsername());


        Glide.with(context).load(selfieList.get(position).getSelfieOriginal()).centerCrop().into(holder.ivSelfie);

        holder.cvSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSlefieClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return selfieList.size();
    }


}
