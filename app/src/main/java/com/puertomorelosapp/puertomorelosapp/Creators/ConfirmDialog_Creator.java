package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 6/28/17.
 */

public class ConfirmDialog_Creator {

    public void showConfirmDialog(@NonNull final Context context, @Nullable String title, @Nullable String message, final IConfirmDialog_Creator listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);


        if (title != null)
            builder.setTitle(title);

        if (message != null)
            builder.setMessage(message);


        final AlertDialog dialogo = builder
                .setPositiveButton(context.getString(R.string.menu_close_session_accept), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onAccept((AlertDialog) dialog);
                    }
                })

                .setNegativeButton(context.getString(R.string.menu_close_session_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onCancel((AlertDialog) dialog);
                    }
                }).create();

        dialogo.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialogo.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.colorPrimary));
                dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            }
        });


        dialogo.show();

    }

    public void showWriteComment(@NonNull final Context context, @Nullable String title, @Nullable String message, final IConfirmComment_Creator listener) {


        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_newcomment);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final Button btDialogAccept = (Button) dialog.findViewById(R.id.btAccept);
        final Button btDialogCancel = (Button) dialog.findViewById(R.id.btCancel);

        final EditText etComment = (EditText) dialog.findViewById(R.id.etWriteComment);


        btDialogAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewComment newComment = new NewComment();
                newComment.setText(etComment.getText().toString());
                listener.onAccept(dialog, newComment);
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
