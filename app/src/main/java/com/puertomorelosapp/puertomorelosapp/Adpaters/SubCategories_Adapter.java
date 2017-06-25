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
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public class SubCategories_Adapter extends RecyclerView.Adapter<SubCategories_Adapter.ViewHolder> {

    private List<SubCategory> subcategoryList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvSubcategoryName;
        public ImageView ivSubcategory;

        public ViewHolder(View v) {
            super(v);
            tvSubcategoryName = (TextView) v.findViewById(R.id.tvSubcategoryName);
            ivSubcategory = (ImageView) v.findViewById(R.id.ivSubcategory);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SubCategories_Adapter(List<SubCategory> subCategories, Context context) {
        this.subcategoryList = subCategories;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SubCategories_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_subcategories_item, parent, false);
        return new SubCategories_Adapter.ViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SubCategories_Adapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvSubcategoryName.setText(subcategoryList.get(position).getTitulo());
        Glide.with(context).load(subcategoryList.get(position).getImageBackgroundContent()).centerCrop().into(holder.ivSubcategory);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return subcategoryList.size();
    }
}
