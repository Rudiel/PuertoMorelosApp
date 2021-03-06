package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.puertomorelosapp.puertomorelosapp.R;


/**
 * Created by rudielavilaperaza on 19/01/17.
 */

public class Adapter_CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public Adapter_CustomInfoWindow(Context context) {

        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_marker_custominfowindow, null, false);

        TextView tvTitle = (TextView) v.findViewById(R.id.tvMarkerTitle);

        TextView tvSubtitle = v.findViewById(R.id.tvMarkerSubtitle);

        tvTitle.setText(marker.getTitle());

        tvSubtitle.setText(marker.getSnippet());

        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {

        return null;
    }
}
