package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies.ISelfieClickListener_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rudielavilaperaza on 9/11/17.
 */

public class Activities_Selfies_Adapter extends RecyclerView.Adapter<Activities_Selfies_Adapter.ViewHolder> {


    private List<Selfie> selfieList;
    private Context context;
    private ISelfieClickListener_Activity listener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivSelfie;
        private ImageView ivProfile;
        private TextView tvCategory;
        private TextView tvCommerce;
        private TextView tvDate;
        private CardView cvActivitySelfie;

        public ViewHolder(View itemView) {
            super(itemView);

            ivSelfie = (ImageView) itemView.findViewById(R.id.ivActivitySelfie);
            ivProfile = (ImageView) itemView.findViewById(R.id.ivActivitySelfieProfile);
            tvCategory = (TextView) itemView.findViewById(R.id.tvActivitySelfieCategory);
            tvCommerce = (TextView) itemView.findViewById(R.id.tvActivitySelfieCommerceName);
            tvDate = (TextView) itemView.findViewById(R.id.tvActivitySelfieDate);
            cvActivitySelfie = (CardView) itemView.findViewById(R.id.cvActivitySelfie);
        }
    }

    public Activities_Selfies_Adapter(Context context, List<Selfie> selfieList, ISelfieClickListener_Activity listener) {

        this.selfieList = selfieList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_activities_selfie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        ViewGroup.LayoutParams params = holder.cvActivitySelfie.getLayoutParams();

        try {
            params.height = displaymetrics.heightPixels / 3 + 40;
            //params.width = displaymetrics.widthPixels / 2;

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.cvActivitySelfie.setLayoutParams(params);

        if (selfieList.get(position).getSubcategoria() != null)
            holder.tvCategory.setText(selfieList.get(position).getSubcategoria());
        else
            holder.tvCategory.setText(selfieList.get(position).getCategoria());

        holder.tvCommerce.setText(selfieList.get(position).getNombreEntidad());

        Glide.with(context).load(selfieList.get(position).getSelfieOriginal()).centerCrop().into(holder.ivSelfie);

        /*SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM HH:mm");
        try {
            Date oneWayTripDate = input.parse(selfieList.get(position).getFecha());// parse input
            holder.tvDate.setText(output.format(oneWayTripDate));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM HH:mm");

        SimpleDateFormat out24 = new SimpleDateFormat("HH:mm");

        int time24 = 24 * 60 * 60 * 1000;
        int time48 = time24 * 2;

        try {
            Date oneWayTripDate = input.parse(selfieList.get(position).getFecha());// parse input
            if ((System.currentTimeMillis() - oneWayTripDate.getTime()) < time24) {
                holder.tvDate.setText(context.getString(R.string.comments_today)  + out24.format(oneWayTripDate));
            } else if ((System.currentTimeMillis() - oneWayTripDate.getTime()) < time48)
                holder.tvDate.setText(context.getString(R.string.comments_yesterday)  + out24.format(oneWayTripDate));
            else
                holder.tvDate.setText(output.format(oneWayTripDate));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String cate;

        if (selfieList.get(position).getSubcategoria() != null) {
            if (selfieList.get(position).getSubcategoria().equals("Restaurantes") ||
                    selfieList.get(position).getSubcategoria().equals("Comida rapida") ||
                    selfieList.get(position).getSubcategoria().equals("Hoteles")
                    )
                cate = selfieList.get(position).getSubcategoria();
            else
                cate = selfieList.get(position).getCategoria();
        } else {
            cate = selfieList.get(position).getCategoria();
        }

        switch (cate) {
            case "Comercios":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_commerce));
                break;
            case "Lugares de interes":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_interesting));
                break;
            case "Historia":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_history));
                break;
            case "Eventos":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_events));
                break;
            case "Servicios":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_service));
                break;
            case "Atractivos Turisticos":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_atractives));
                break;
            case "Hoteles":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_hotel));
                break;
            case "Restaurantes":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_restaurant));
                break;
            case "Comida rapida":
                holder.ivProfile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_fastfood));
                break;
            default:
                break;
        }

        holder.cvActivitySelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelfieClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return selfieList.size();
    }


}
