package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.IOnclickCategories;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/12/17.
 */

public class Comments_Adapter extends RecyclerView.Adapter<Comments_Adapter.ViewHolder> {

    private List<Comments> commentsList;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView tvCommentName;
        private ImageView ivProfile;
        private TextView tvCommentText;
        private TextView tvCommentDate;


        private ViewHolder(View v) {
            super(v);
            tvCommentDate = (TextView) v.findViewById(R.id.tvCommentsDetailDate);
            tvCommentName = (TextView) v.findViewById(R.id.tvCommentsDetailUser);
            tvCommentText = (TextView) v.findViewById(R.id.tvCommentsDetailText);
            ivProfile = (ImageView) v.findViewById(R.id.ivCommentsProfile);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Comments_Adapter(List<Comments> commentsList, Context context) {
        this.context = context;
        this.commentsList = commentsList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_comments_detail_item, parent, false);
        return new Comments_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvCommentText.setText(commentsList.get(position).getText());
        holder.tvCommentName.setText(commentsList.get(position).getSenderDisplayName());
        holder.tvCommentDate.setText(commentsList.get(position).getFecha());

        if (commentsList.get(position).getImageURL().equals("SomeimageURL") || commentsList.get(position).getImageURL().equals("")) {
            holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_deafult));
        } else {

            Glide.with(context).load(commentsList.get(position).getImageURL()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivProfile) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.ivProfile.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }


}
