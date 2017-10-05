package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Services_Adapter;
import com.puertomorelosapp.puertomorelosapp.Models.Servicio;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.List;

/**
 * Created by rudielavilaperaza on 7/3/17.
 */

public class ServicesDialog {

    public void showServices(Context context, List<Servicio> servicios) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_dialog_services, null);

        final Dialog dialog =
                builder.setView(view).create();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        final RecyclerView rvServices = (RecyclerView) view.findViewById(R.id.rvServices);

        ((TextView)view.findViewById(R.id.tvDialogServiceName)).setTypeface(Utils.getbukharisLetter(context));

        rvServices.setLayoutManager(new LinearLayoutManager(context));

        rvServices.setHasFixedSize(true);

        rvServices.setAdapter(new Services_Adapter(servicios, context));


        dialog.show();
    }

}
