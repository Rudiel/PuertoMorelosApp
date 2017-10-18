package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 6/28/17.
 */

public class ConfirmDialog_Creator {

    public void showConfirmDialog(@NonNull final Context context, @Nullable String title, @Nullable String message, final IConfirmDialog_Creator listener) {

        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final Button btDialogOK = (Button) dialog.findViewById(R.id.btOK);

        final TextView tvDialogTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        final TextView tvDialogMessage = (TextView) dialog.findViewById(R.id.tvMessage);

        final LinearLayout llButtons = (LinearLayout) dialog.findViewById(R.id.llButtons);

        final Button btAccept = dialog.findViewById(R.id.btAccept);
        final Button btCancel = dialog.findViewById(R.id.btCancel);

        if (title != null)
            tvDialogTitle.setText(title);

        if (message != null)
            tvDialogMessage.setText(message);

        llButtons.setVisibility(View.VISIBLE);

        btDialogOK.setVisibility(View.GONE);

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAccept(dialog);
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCancel(dialog);
            }
        });


        dialog.show();

    }

    public void showWriteComment(@NonNull final Context context, @Nullable String title, @Nullable String message, final IConfirmComment_Creator listener, @Nullable final Comments comment) {


        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_newcomment);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        final Button btDialogAccept = (Button) dialog.findViewById(R.id.btAccept);
        final Button btDialogCancel = (Button) dialog.findViewById(R.id.btCancel);

        final EditText etComment = (EditText) dialog.findViewById(R.id.etWriteComment);

        if (comment != null) {
            etComment.setText(comment.getText());
        }

        btDialogAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etComment.getText().toString().trim().equals("")) {
                    if (comment == null) {
                        NewComment newComment = new NewComment();
                        newComment.setText(etComment.getText().toString());
                        listener.onAccept(dialog, newComment);
                    } else {
                        //editamos el comentario
                        comment.setText(etComment.getText().toString());
                        listener.onEdit(dialog, comment);
                    }
                }

            }
        });

        btDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel(dialog);
            }
        });

        dialog.show();


    }
}
