package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes.IDelete_Like_Listener;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;
import com.puertomorelosapp.puertomorelosapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rudielavilaperaza on 9/11/17.
 */

public class Activities_Likes_Adapter extends RecyclerView.Adapter<Activities_Likes_Adapter.ViewHolder> {

    private List<Like> likeList;
    private Context context;
    private IDelete_Like_Listener deleteListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLikesPlace;
        private TextView tvLikesCategory;
        private TextView tvDate;
        private ImageView ivLikesImage;
        private LinearLayout llDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvLikesPlace = (TextView) itemView.findViewById(R.id.tvLikesPlace);
            tvLikesCategory = (TextView) itemView.findViewById(R.id.tvLikesCategory);
            tvDate = (TextView) itemView.findViewById(R.id.tvLikesDate);
            ivLikesImage = (ImageView) itemView.findViewById(R.id.ivLikesImage);
            llDelete = (LinearLayout) itemView.findViewById(R.id.llEliminar);
        }
    }

    public Activities_Likes_Adapter(Context context, List<Like> likes, IDelete_Like_Listener deleteListener) {
        this.likeList = likes;
        this.context = context;
        this.deleteListener = deleteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_activities_likes_item, parent, false);
        return new Activities_Likes_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvLikesPlace.setText(likeList.get(position).getNombreEntidad());
        holder.tvLikesCategory.setText(likeList.get(position).getCategoria());

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            holder.tvDate.setText(getFormattedDate(input.parse(likeList.get(position).getFecha())));
        } catch (Exception e) {
            e.printStackTrace();
        }


        String cate;

        if (likeList.get(position).getSubcategoria() != null) {
            if (likeList.get(position).getSubcategoria().equals("Restaurantes") ||
                    likeList.get(position).getSubcategoria().equals("Comida rapida") ||
                    likeList.get(position).getSubcategoria().equals("Hoteles")
                    )
                cate = likeList.get(position).getSubcategoria();
            else
                cate = likeList.get(position).getCategoria();
        } else {
            cate = likeList.get(position).getCategoria();
        }

        switch (cate) {
            case "Comercios":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_commerce));
                break;
            case "Lugares de interes":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_interesting));
                break;
            case "Historia":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_history));
                break;
            case "Eventos":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_events));
                break;
            case "Servicios":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_service));
                break;
            case "Atractivos Turisticos":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_atractives));
                break;
            case "Hoteles":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_hotel));
                break;
            case "Restaurantes":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_restaurant));
                break;
            case "Comida rapida":
                holder.ivLikesImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_fastfood));
                break;
            default:
                break;
        }

        holder.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDeleteListener(likeList.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return likeList.size();
    }


    private String getFormattedDate(Date datePic) {

        Calendar picTime = Calendar.getInstance();
        picTime.setTimeInMillis(datePic.getTime());

        Calendar currentTime = Calendar.getInstance();


        SimpleDateFormat output = new SimpleDateFormat("dd MMM HH:mm");

        SimpleDateFormat out24 = new SimpleDateFormat("HH:mm");

        if (currentTime.get(Calendar.DAY_OF_YEAR) == picTime.get(Calendar.DAY_OF_YEAR)) {
            return context.getString(R.string.comments_today) + " " + out24.format(datePic);
        } else if (currentTime.get(Calendar.DAY_OF_YEAR) - picTime.get(Calendar.DAY_OF_YEAR) == 1) {
            return context.getString(R.string.comments_yesterday) + " " + out24.format(datePic);
        } else {
            return output.format(datePic);
        }

    }
}
