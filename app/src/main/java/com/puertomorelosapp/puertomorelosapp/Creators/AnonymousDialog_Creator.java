package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 11/13/17.
 */

public class AnonymousDialog_Creator {

    public Dialog showAnonymousDialog(Context context, @Nullable String title, @Nullable String message, final IAnonymousListener listener) {

        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.layout_anonymous_creator);

        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;

        Button btRegisterNow = dialog.findViewById(R.id.btRegisterNow);

        Button btRegisterLate = dialog.findViewById(R.id.btRegisterLate);

        if (title != null) {
            ((TextView) (dialog.findViewById(R.id.tvAnonymousTitle))).setText(title);
        }

        if (message != null) {
            ((TextView) (dialog.findViewById(R.id.tvAnonymousMessage))).setText(message);
        }

        btRegisterLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRegisterLate(dialog);
            }
        });

        btRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRegisterNow(dialog);
            }
        });

        return dialog;
    }
}
