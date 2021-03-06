package com.puertomorelosapp.puertomorelosapp.Fragments.Map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Adapter_CustomInfoWindow;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Place;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rudielavilaperaza on 6/11/17.
 */

public class Fragment_Map extends Fragment implements OnMapReadyCallback, IMap_View {

    private GoogleMap map;
    private MapView mapView;

    @Inject
    IMap_Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_map, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((Main_Activity) getActivity()).ivMap.setVisibility(View.GONE);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getActivity());

        map = googleMap;

        setStyle(map);

        presenter.getPlaces();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20.852656, -86.889002), 13.0f));

        map.setInfoWindowAdapter(new Adapter_CustomInfoWindow(getActivity()));


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
        } else {
            map.setMyLocationEnabled(true);
        }


    }

    @Override
    public void showPlaces(List<Place> places) {
        for (Place place : places) {
            if (!place.getLatitud().equals("") && !place.getLongitud().equals("")) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(Double.parseDouble(place.getLatitud()), Double.parseDouble(place.getLongitud())));

                switch (place.getCategoria()) {
                    case "Comercios":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_comercio));
                        break;
                    case "Eventos":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_events));
                        break;
                    case "Lugares de interes":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_interestingplace));
                        break;
                    case "Servicios":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_services));
                        break;
                    case "Historia":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_history));
                        break;
                }
                if (place.getSubcategoria() != null) {
                    switch (place.getSubcategoria()) {
                        case "Hoteles":
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hotel));
                            break;
                        case "Comida rapida":
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fastfood));
                            break;
                        case "Restaurantes":
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant));
                            break;
                    }
//Html.fromHtml("<html><body><font size=5 color=red>Hello </font> World </body><html>")
                    markerOptions.snippet(place.getSubcategoria());
                } else
                    markerOptions.snippet(place.getCategoria());
                markerOptions.title(place.getNombre());

                map.addMarker(markerOptions);
            }
        }
    }

    private void setStyle(GoogleMap map) {

        try {
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json));

        } catch (Resources.NotFoundException e) {

        }

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
