package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 6/12/17.
 */

public class Dialog_Creator {

    public void showAlertDialog(Context context, @Nullable String title, @Nullable String message, final IDialog_Creator dialog_creator) {

        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        final Button btDialogOK = (Button) dialog.findViewById(R.id.btOK);

        final TextView tvDialogTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        final TextView tvDialogMessage = (TextView) dialog.findViewById(R.id.tvMessage);

        final LinearLayout llButtons = (LinearLayout) dialog.findViewById(R.id.llButtons);

        if (title != null)
            tvDialogTitle.setText(title);

        if (message != null)
            tvDialogMessage.setText(message);

        llButtons.setVisibility(View.GONE);

        btDialogOK.setVisibility(View.VISIBLE);

        btDialogOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_creator.didOK(dialog);
            }
        });

        dialog.show();


    }

}
