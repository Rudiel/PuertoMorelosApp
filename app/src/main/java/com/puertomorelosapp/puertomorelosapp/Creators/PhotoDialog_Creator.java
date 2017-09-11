package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

/**
 * Created by rudielavilaperaza on 8/18/17.
 */

public class PhotoDialog_Creator {


    public void showNewPhotoDialog(final Context context, byte[] bytes, final INewPhoto_Creator listener) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_newphoto);

        final ImageView ivNewPhoto = (ImageView) dialog.findViewById(R.id.ivNewPhoto);
        final EditText etNewPhotoComment = (EditText) dialog.findViewById(R.id.etNewPhotoComment);
        final TextView tvNewPhotoCounter = (TextView) dialog.findViewById(R.id.tvNewPhotoCounter);
        final ImageView ivNewPhotoProfilePicture = (ImageView) dialog.findViewById(R.id.ivNewPhotoProfilePicture);
        final Button btNewPhotoConfirm = (Button) dialog.findViewById(R.id.btNewPhotoPublish);
        final Button btNewPhotoCancel = (Button) dialog.findViewById(R.id.btNewPhotoCancel);
        final TextView tvNewPhotoUserName = (TextView) dialog.findViewById(R.id.tvNewPhotoUserName);

        /*Glide.with(context).load(bytes).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivNewPhoto) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivNewPhoto.setImageDrawable(circularBitmapDrawable);
            }
        });*/

        Glide.with(context).load(bytes).listener(new RequestListener<byte[], GlideDrawable>() {
            @Override
            public boolean onException(Exception e, byte[] model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, byte[] model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).centerCrop().into(ivNewPhoto);

        etNewPhotoComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() > 79) {
                    tvNewPhotoCounter.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
                } else
                    tvNewPhotoCounter.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                tvNewPhotoCounter.setText(String.valueOf(89 - s.toString().length()));

            }
        });


        btNewPhotoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancelClick(dialog);
            }
        });

        btNewPhotoConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onConfirmClick(dialog);
            }
        });

        if (Utils.getUserImage(context).equals("SomeimageURL")) {
            Glide.with(context).load(R.drawable.avatar_deafult).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivNewPhotoProfilePicture) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivNewPhotoProfilePicture.setImageDrawable(circularBitmapDrawable);
                }
            });
        } else {
            Glide.with(context).load(Utils.getUserImage(context)).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivNewPhotoProfilePicture) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivNewPhotoProfilePicture.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

        tvNewPhotoUserName.setText(Utils.getUserName(context));


        dialog.show();

    }

}
