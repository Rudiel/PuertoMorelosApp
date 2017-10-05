package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Models.Servicio;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 7/3/17.
 */

public class Services_Adapter extends RecyclerView.Adapter<Services_Adapter.ViewHolder> {

    private List<Servicio> servicioList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView name;
        private ImageView image;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.tvServiceName);
            image = (ImageView) v.findViewById(R.id.ivServiceImage);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Services_Adapter(List<Servicio> subCategories, Context context) {
        this.servicioList = subCategories;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Services_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_services_item, parent, false);
        return new Services_Adapter.ViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final Services_Adapter.ViewHolder holder, final int position) {

        //holder.tvSubcategoryName.setText(subcategoryList.get(position).getNombre());
        //Glide.with(context).load(subcategoryList.get(position).getImageBackgroundContent()).centerCrop().into(holder.ivSubcategory);
        holder.name.setText(servicioList.get(position).getName());
        holder.image.setImageDrawable(servicioList.get(position).getDrawable());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return servicioList.size();
    }
}
