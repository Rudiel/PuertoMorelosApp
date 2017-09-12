package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public ViewHolder(View itemView) {
            super(itemView);

            tvComment = (TextView) itemView.findViewById(R.id.tvCommentsComment);
            tvCategory = (TextView) itemView.findViewById(R.id.tvCommentsCategory);
            tvDate = (TextView) itemView.findViewById(R.id.tvCommentsDate);
            tvPlace = (TextView) itemView.findViewById(R.id.tvCommentsPlace);
            tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
            tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);
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


        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM HH:mm");
        try {
            Date oneWayTripDate = input.parse(commentsList.get(position).getFecha());
            holder.tvDate.setText(output.format(oneWayTripDate));

        } catch (ParseException e) {
            e.printStackTrace();
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
