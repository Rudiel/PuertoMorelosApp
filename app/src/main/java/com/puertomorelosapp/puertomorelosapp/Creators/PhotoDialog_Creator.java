package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.puertomorelosapp.puertomorelosapp.R;

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

        Glide.with(context).load(bytes).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivNewPhoto) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivNewPhoto.setImageDrawable(circularBitmapDrawable);
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



        dialog.show();

    }

}
