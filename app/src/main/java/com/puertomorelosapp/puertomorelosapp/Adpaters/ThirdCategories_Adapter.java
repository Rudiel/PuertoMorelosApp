package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main.IOnclikThirdListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/27/17.
 */

public class ThirdCategories_Adapter extends RecyclerView.Adapter<ThirdCategories_Adapter.ViewHolder> {

    private List<Categorie> thirdcategoryList;
    private Context context;
    private IOnclikThirdListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView tvThirdCategory;
        private ImageView ivThirdCategory;


        public ViewHolder(View v) {
            super(v);
            tvThirdCategory = (TextView) v.findViewById(R.id.tvThirdCategory);
            ivThirdCategory = (ImageView) v.findViewById(R.id.ivThirdCategory);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ThirdCategories_Adapter(List<Categorie> thirdCategories, Context context, IOnclikThirdListener listener) {
        this.thirdcategoryList = thirdCategories;
        this.context = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ThirdCategories_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_thirdcategories_item, parent, false);
        return new ThirdCategories_Adapter.ViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ThirdCategories_Adapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvThirdCategory.setText(String.valueOf(thirdcategoryList.get(position).getName()));
        Glide.with(context).load(thirdcategoryList.get(position).getImage()).centerCrop().into(holder.ivThirdCategory);
        holder.ivThirdCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickThirdListener(thirdcategoryList.get(position));
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return thirdcategoryList.size();
    }


}
