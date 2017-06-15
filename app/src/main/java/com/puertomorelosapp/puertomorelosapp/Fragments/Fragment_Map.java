package com.puertomorelosapp.puertomorelosapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.R;

/**
 * Created by rudielavilaperaza on 6/11/17.
 */

public class Fragment_Map extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_map, container, false);
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
        setMarkers();

        map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(20.852656, -86.889002) , 13.0f) );

    }

    private void setMarkers() {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(20.854918, -86.901747));
        markerOptions.title("Testing");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atractivos));
        map.addMarker(markerOptions);

        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(new LatLng(20.856883, -86.898227));
        markerOptions1.title("Testing");
        markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_comercio));
        map.addMarker(markerOptions1);


        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(new LatLng(20.859530, -86.902304));
        markerOptions2.title("Testing");
        markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_events));
        map.addMarker(markerOptions2);

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(new LatLng(20.848661, -86.875182));
        markerOptions3.title("Testing");
        markerOptions3.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fastfood));
        map.addMarker(markerOptions3);

        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(new LatLng(20.856498, -86.872413));
        markerOptions4.title("Testing");
        markerOptions4.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_history));
        map.addMarker(markerOptions4);

        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(new LatLng(20.848589, -86.900368));
        markerOptions5.title("Testing");
        markerOptions5.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_interestingplace));
        map.addMarker(markerOptions5);

        MarkerOptions markerOptions6 = new MarkerOptions();
        markerOptions6.position(new LatLng(20.862105, -86.908822));
        markerOptions6.title("Testing");
        markerOptions6.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hotel));
        map.addMarker(markerOptions6);

        //20.859530, -86.902304
        //20.848661, -86.875182
        //20.856498, -86.872413
        //20.848589, -86.900368
        //20.862105, -86.908822
        //20.865759, -86.900167

    }

}
