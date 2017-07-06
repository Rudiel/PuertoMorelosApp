package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.puertomorelosapp.puertomorelosapp.Creators.ServicesDialog;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public class Detail_Fragment extends Fragment {

    private static View view;

    private SubCategory subCategory;

    //Collapsin toolbar

    @Bind(R.id.appBar)
    AppBarLayout appBarLayout;

    @Bind(R.id.tbDetail)
    Toolbar tbDetail;

    @Bind(R.id.collapsinToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Inject
    IDetail_Presenter presenter;

    //Views

    @Bind(R.id.ivDetail)
    ImageView ivDetail;

    @Bind(R.id.tvDetailLikes)
    TextView tvLikes;

    @Bind(R.id.tvDetailComments)
    TextView tvComments;

    @Bind(R.id.tvDetailNombre)
    TextView tvNombre;

    @Bind(R.id.tvDetailDescription)
    TextView tvDescripcion;

    @Bind(R.id.tvDetailTitle)
    TextView tvTitle;

    @Bind(R.id.tvDetailHorario)
    TextView tvHorario;

    @Bind(R.id.tvDetailFechaDia)
    TextView tvFechaDia;

    @Bind(R.id.tvDetailTelefono)
    TextView tvTelefono;

    @Bind(R.id.tvDetailDireccion)
    TextView tvDireccion;

    @Bind(R.id.tvDetailTitle2)
    TextView tvTitle2;

    @Bind(R.id.tvDetailDescription2)
    TextView tvDescripcion2;

    @Bind(R.id.tvDetailTitle3)
    TextView tvTitle3;

    @Bind(R.id.tvDetailDescription3)
    TextView tvDescripcion3;

    @Bind(R.id.tvDetailAcces)
    TextView tvAcceso;

    @Bind(R.id.llDetailServices)
    LinearLayout llDetailServices;

    @Bind(R.id.viewServices)
    View viewServices;

    private GoogleMap map;

    SupportMapFragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.layout_deatil, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((Main_Activity) getActivity()).toolbar.setVisibility(View.GONE);

        subCategory = ((Main_Activity) getActivity()).subCategory;

        appBarLayout.setExpanded(true);

        //Seteamos los datos
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivDetail.setTransitionName(subCategory.getNombre());
        }

        if (subCategory.getImageBackgroundContent() != null)
            Glide.with(getActivity()).load(subCategory.getImageBackgroundContent()).into(ivDetail);

        tvLikes.setText(String.valueOf(subCategory.getLikes()));

        tvComments.setText(String.valueOf(subCategory.getComments()));

        tvDescripcion.setText(subCategory.getDescripcion());

        tvNombre.setText(subCategory.getNombre());

        tvTitle.setText(subCategory.getTitulo());

        if (subCategory.getFechadias() != null) {
            tvFechaDia.setVisibility(View.VISIBLE);
            tvFechaDia.setText(subCategory.getFechadias());
        } else
            tvFechaDia.setVisibility(View.GONE);

        if (subCategory.getHorainicio() != null & subCategory.getHorafin() != null) {
            tvHorario.setVisibility(View.VISIBLE);
            tvHorario.setText(subCategory.getHorainicio() + " - " + subCategory.getHorafin());
        } else
            tvHorario.setVisibility(View.GONE);

        if (subCategory.getTelefono() != null) {
            tvTelefono.setVisibility(View.VISIBLE);
            tvTelefono.setText(subCategory.getTelefono());
        } else {
            tvTelefono.setVisibility(View.GONE);
        }

        if (subCategory.getDireccion() != null) {
            tvDireccion.setText(subCategory.getDireccion());
            tvDireccion.setVisibility(View.VISIBLE);
        } else
            tvDireccion.setVisibility(View.GONE);

        if (subCategory.getDescripcion2() != null) {
            tvDescripcion2.setVisibility(View.VISIBLE);
            tvDescripcion2.setText(subCategory.getDescripcion2());
        } else
            tvDescripcion2.setVisibility(View.GONE);

        if (subCategory.getTitulo2() != null) {
            tvTitle2.setVisibility(View.VISIBLE);
            tvTitle2.setText(subCategory.getTitulo2());
        } else
            tvTitle2.setVisibility(View.GONE);

        if (subCategory.getDescripcion3() != null) {
            tvDescripcion3.setVisibility(View.VISIBLE);
            tvDescripcion3.setText(subCategory.getDescripcion3());
        } else
            tvDescripcion3.setVisibility(View.GONE);

        if (subCategory.getTitulo3() != null) {
            tvTitle3.setVisibility(View.VISIBLE);
            tvTitle3.setText(subCategory.getTitulo3());
        } else
            tvTitle3.setVisibility(View.GONE);

        if (subCategory.getAcceso() != null) {
            tvAcceso.setVisibility(View.VISIBLE);
            tvAcceso.setText(subCategory.getAcceso());
        } else
            tvAcceso.setVisibility(View.GONE);

        llDetailServices.removeAllViews();

        if (subCategory.getServicios() != null) {
            showServices();
            llDetailServices.setVisibility(View.VISIBLE);
            viewServices.setVisibility(View.VISIBLE);
        } else {
            llDetailServices.setVisibility(View.GONE);
            viewServices.setVisibility(View.GONE);
        }

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    if (getActivity() != null) {
                        collapsingToolbarLayout.setTitle(subCategory.getNombre());
                        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
                        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
                        collapsingToolbarLayout.setCollapsedTitleTypeface(Utils.getbukharisLetter(getActivity()));
                    }

                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        initMap();

    }

    private void showServices() {

        //Click al view e inflar la vista en un dialogo

        for (int i = 0; i < subCategory.getServicios().size(); i++) {

            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);

            switch (subCategory.getServicios().get(i).getName()) {
                case "Estacionamiento":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_local_parking_black_48dp));
                    break;
                case "Multiples idiomas":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_public_black_48dp));
                    break;
                case "Pago con tarjeta":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_payment_black_48dp));
                    break;
                case "Servicio a domicilio":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_local_shipping_black_48dp));
                    break;
                case "Wifi":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_wifi_black_48dp));
                    break;
                case "Aire acondicionado":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_ac_unit_black_48dp));
                    break;
                case "Recepción":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_room_service_black_48dp));
                    break;

            }

            Drawable image = imageView.getDrawable();
            image.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);
            imageView.setImageDrawable(image);

            subCategory.getServicios().get(i).setDrawable(image);

            if (i < 3) {
                llDetailServices.addView(imageView);
            }

        }

        llDetailServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServicesDialog().showServices(getActivity(), subCategory.getServicios());
            }
        });

    }

    private void initMap() {
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.fgMapDetail);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.fgMapDetail, fragment).commit();
        }

        fragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                try {
                    map = googleMap;
                    MarkerOptions marker = new MarkerOptions();
                    LatLng position = new LatLng(Double.parseDouble(subCategory.getLatitud()), Double.parseDouble(subCategory.getLongitud()));
                    marker.position(position);

                    Log.d("CATEGORIA", ((Main_Activity) getActivity()).category.getName());
                    Log.d("SUCATEGORIA", ((Main_Activity) getActivity()).subCategory.getTitulo());

                    switch (((Main_Activity) getActivity()).category.getName()) {
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

                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 18));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }


}
