package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.ViewHolder> {

    private List<Categorie> categorieList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvCategoryName;
        public ImageView ivImageCategory;

        public ViewHolder(View v) {
            super(v);
            tvCategoryName = (TextView) v.findViewById(R.id.tvCategoryName);
            ivImageCategory = (ImageView) v.findViewById(R.id.ivImageCategoryBack);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Categories_Adapter(List<Categorie> categories, Context context) {
        this.categorieList = categories;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Categories_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_categories, parent, false);
        return new ViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvCategoryName.setText(categorieList.get(position).getName());

        Glide.with(context).load(categorieList.get(position).getImage()).centerCrop().into(holder.ivImageCategory);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categorieList.size();
    }
}
