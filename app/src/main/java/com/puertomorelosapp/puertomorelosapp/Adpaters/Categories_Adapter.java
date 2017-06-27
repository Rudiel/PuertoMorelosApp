package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.IOnclickCategories;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.ViewHolder> {

    private List<Categorie> categorieList;
    private Context context;
    private IOnclickCategories listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvCategoryName;
        public ImageView ivImageCategory;
        public ImageView ivCateItem;
        public RelativeLayout rlContainer;

        public ViewHolder(View v) {
            super(v);
            tvCategoryName = (TextView) v.findViewById(R.id.tvCategoryName);
            ivImageCategory = (ImageView) v.findViewById(R.id.ivImageCategoryBack);
            rlContainer = (RelativeLayout) v.findViewById(R.id.rlCategoryContainer);
            ivCateItem = (ImageView) v.findViewById(R.id.ivCateItem);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Categories_Adapter(List<Categorie> categories, Context context, IOnclickCategories listener) {
        this.categorieList = categories;
        this.context = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Categories_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_categories, parent, false);
        return new ViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvCategoryName.setText(categorieList.get(position).getName().toUpperCase());

        switch (categorieList.get(position).getName()) {
            case "Historia":
                holder.ivCateItem.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_account_balance_white_48dp));
                break;
            case "Restaurantes":
                holder.ivCateItem.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_restaurant_white_48dp));
                break;
            case "Hoteles":
                holder.ivCateItem.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_hotel_white_48dp));
                break;
            case "Atractivos turisticos":
                break;
            default:
                holder.ivCateItem.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_dialog_info));
                break;
        }

        Glide.with(context).load(categorieList.get(position).getImage()).centerCrop().into(holder.ivImageCategory);
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCategory(categorieList.get(position));
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categorieList.size();
    }
}
