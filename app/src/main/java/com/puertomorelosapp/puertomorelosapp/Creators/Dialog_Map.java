package com.puertomorelosapp.puertomorelosapp.Creators;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 9/26/17.
 */

public class Dialog_Map extends DialogFragment {

    private SubCategory subCategory;
    private static View view;

    private SupportMapFragment fragment;
    private GoogleMap map;


    public Dialog_Map() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.layout_detail_map, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }


        Dialog d = getDialog();

        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.layout_dialog_gallery);
        
        Window window = d.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(R.color.trans_black)));

        d.setCancelable(true);

        d.setCanceledOnTouchOutside(true);

        d.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        subCategory = (SubCategory) getArguments().getSerializable("Place");

        Log.d("SUB_MAPA", subCategory.getLatitud().toString());

        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.mapDetailDialog);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.mapDetailDialog, fragment).commit();
        }

        fragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                map = googleMap;
                try {

                    try {
                        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json));

                    } catch (Resources.NotFoundException e) {

                    }

                    MarkerOptions marker = new MarkerOptions();
                    LatLng position = new LatLng(Double.parseDouble(subCategory.getLatitud()), Double.parseDouble(subCategory.getLongitud()));
                    marker.position(position);

                    Log.d("CATEGORIA", ((Main_Activity) getActivity()).category.getName());
                    Log.d("SUCATEGORIA", ((Main_Activity) getActivity()).subCategory.getTitulo());
                    Log.d("MAIN_CATE", ((Main_Activity) getActivity()).mainCategory);


                    switch (((Main_Activity) getActivity()).mainCategory) {
                        case "Restaurantes":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant));
                            break;
                        case "Historia":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_history));
                            break;
                        case "Eventos":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_events));
                            break;
                        case "Comercios":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_comercio));
                            break;
                        case "Servicios":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_services));
                            break;
                        case "Comida rapida":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fastfood));
                            break;
                        case "Atractivos Turisticos":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atractivos));
                            break;
                        case "Lugares de interes":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_interestingplace));
                            break;
                        case "Hoteles":
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hotel));
                            break;
                    }

                    map.addMarker(marker);

                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
                    } else {
                        map.setMyLocationEnabled(true);
                    }


                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16));


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);
            }
        }
    }


}
