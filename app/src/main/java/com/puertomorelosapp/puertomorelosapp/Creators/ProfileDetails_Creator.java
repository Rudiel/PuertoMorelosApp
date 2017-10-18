package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.puertomorelosapp.puertomorelosapp.Models.Response.ProfileInfo;
import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 18/10/17.
 */

public class ProfileDetails_Creator {

    public void showDetails(final Context context, ProfileInfo info) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_dialog_profileinfo, null);

        final Dialog dialog =
                builder.setView(view).create();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        TextView tvProfileName = view.findViewById(R.id.tvProfileName);
        TextView tvProfileLikes = view.findViewById(R.id.tvProfileLikes);
        TextView tvProfileComments = view.findViewById(R.id.tvProfileComments);
        final ImageView ivProfile = view.findViewById(R.id.ivProfile);
        ImageView ivProfileLikes= view.findViewById(R.id.ivProfileLikes);
        ImageView ivProfileComments= view.findViewById(R.id.ivProfileComments);

        tvProfileName.setText(info.getName());
        tvProfileLikes.setText(String.valueOf(info.getLikes()));
        tvProfileComments.setText(String.valueOf(info.getComments()));

        if (info.getImage().equals("SomeimageURL") || info.getImage().equals("")) {
            ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_deafult));
        } else {

            Glide.with(context).load(info.getImage()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivProfile) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivProfile.setImageDrawable(circularBitmapDrawable);
                }
            });
        }


        ivProfileLikes.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
        ivProfileComments.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);


        dialog.show();
    }
}
