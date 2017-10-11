package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments.IComments_Listener;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rudielavilaperaza on 9/11/17.
 */

public class Activities_Comments_Adapter extends RecyclerView.Adapter<Activities_Comments_Adapter.ViewHolder> {

    private List<Comments> commentsList;
    private Context context;
    private IComments_Listener listener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvComment;
        private TextView tvDate;
        private TextView tvCategory;
        private TextView tvPlace;
        private TextView tvEdit;
        private TextView tvDelete;
        private ImageView ivCommentsImage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvComment = (TextView) itemView.findViewById(R.id.tvCommentsComment);
            tvCategory = (TextView) itemView.findViewById(R.id.tvCommentsCategory);
            tvDate = (TextView) itemView.findViewById(R.id.tvCommentsDate);
            tvPlace = (TextView) itemView.findViewById(R.id.tvCommentsPlace);
            tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
            tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);
            ivCommentsImage = (ImageView) itemView.findViewById(R.id.ivCommentsImage);
        }
    }


    public Activities_Comments_Adapter(Context context, List<Comments> commentsList, IComments_Listener listener) {
        this.context = context;
        this.commentsList = commentsList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_activities_comments_item, parent, false);
        return new Activities_Comments_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvPlace.setText(commentsList.get(position).getNombreEntidad());
        holder.tvCategory.setText(commentsList.get(position).getCategoria());
        holder.tvComment.setText(commentsList.get(position).getText());


       /* SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM HH:mm");
        try {
            Date oneWayTripDate = input.parse(commentsList.get(position).getFecha());
            holder.tvDate.setText(output.format(oneWayTripDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM HH:mm");

        SimpleDateFormat out24 = new SimpleDateFormat("HH:mm");

        int time24 = 24 * 60 * 60 * 1000;
        int time48 = time24 * 2;


        try {
            Date oneWayTripDate = input.parse(commentsList.get(position).getFecha());// parse input
            if ((System.currentTimeMillis() - oneWayTripDate.getTime()) < time24) {
                holder.tvDate.setText(context.getString(R.string.comments_today) + out24.format(oneWayTripDate));
            } else if ((System.currentTimeMillis() - oneWayTripDate.getTime()) < time48)
                holder.tvDate.setText(context.getString(R.string.comments_yesterday)+ out24.format(oneWayTripDate));
            else
                holder.tvDate.setText(output.format(oneWayTripDate));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String cate;

        if (commentsList.get(position).getSubcategoria() != null) {
            if (commentsList.get(position).getSubcategoria().equals("Restaurantes") ||
                    commentsList.get(position).getSubcategoria().equals("Comida rapida") ||
                    commentsList.get(position).getSubcategoria().equals("Hoteles")
                    )
                cate = commentsList.get(position).getSubcategoria();
            else
                cate = commentsList.get(position).getCategoria();
        } else {
            cate = commentsList.get(position).getCategoria();
        }

        switch (cate) {
            case "Comercios":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_commerce));
                break;
            case "Lugares de interes":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_interesting));
                break;
            case "Historia":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_history));
                break;
            case "Eventos":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_events));
                break;
            case "Servicios":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_service));
                break;
            case "Atractivos Turisticos":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_atractives));
                break;
            case "Hoteles":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_hotel));
                break;
            case "Restaurantes":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_restaurant));
                break;
            case "Comida rapida":
                holder.ivCommentsImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_activity_fastfood));
                break;
            default:
                break;
        }

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(commentsList.get(position));
            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditComment(commentsList.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }


}
