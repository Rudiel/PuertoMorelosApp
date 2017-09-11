package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.puertomorelosapp.puertomorelosapp.Creators.ServicesDialog;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments.Comments_Detail_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos.Photos_Detail_Fragment;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;
import com.puertomorelosapp.puertomorelosapp.Models.Servicio;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public class Detail_Fragment extends Fragment implements IDetail_View {

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

    @Bind(R.id.ivDetailComments)
    ImageView ivDetailComments;

    @Bind(R.id.ivDetailGalery)
    ImageView ivDetailGalery;

    @Bind(R.id.tvDetailGallery)
    TextView tvDetailGallery;

    @Bind(R.id.tvDetailDescriptionTitle)
    TextView tvDetailDescriptionTitle;

    @Bind(R.id.tvDetailUbicationTitle)
    TextView tvDetailUbicationTitle;

    @Bind(R.id.ivDetailLikes)
    ImageView ivDetailLikes;

    private GoogleMap map;

    SupportMapFragment fragment;

    private boolean isDelete = false;

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

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);


        ButterKnife.bind(this, view);

        presenter.setView(Detail_Fragment.this);

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

        presenter.getLikes(this.subCategory, ((Main_Activity) getActivity()).category);

        presenter.getLikeActive(Utils.getProvider(getActivity()), subCategory.getId());

        presenter.getPhotosNumber(this.subCategory, ((Main_Activity) getActivity()).category);


        tvComments.setText(String.valueOf(subCategory.getComments()));

        tvDescripcion.setText(subCategory.getDescripcion());

        tvNombre.setText(subCategory.getNombre());

        tvTitle.setText(subCategory.getTitulo());

        tvComments.setText(String.valueOf(subCategory.getComments()));

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

        ivDetailComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity) getActivity()).setFragment(new Comments_Detail_Fragment(), true, null);
            }
        });

        ivDetailGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity) getActivity()).setFragment(new Photos_Detail_Fragment(), true, null);
            }
        });

        ivDetailLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLike();
            }
        });


    }

    private void showServices() {

        //Click al view e inflar la vista en un dialogo

        final List<Servicio> servicioList = new ArrayList<>();

        int i = 0;

        for (String key : subCategory.getServicios().keySet()) {

            i += 1;

            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);

            Servicio servicio = new Servicio();

            if (key.equals("Estacionamiento")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_local_parking_black_48dp));

            } else if (key.equals("Multiples idiomas")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_public_black_48dp));

            } else if (key.equals("Pago con tarjeta")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_payment_black_48dp));
            } else if (key.equals("Wifi")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_wifi_black_48dp));
            } else if (key.equals("Aire acondicionado")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_ac_unit_black_48dp));

            } else if (key.equals("Recepción")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_room_service_black_48dp));

            } else if (key.equals("Servicio a domicilio")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_local_shipping_black_48dp));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_info_outline_black_48dp));
            }

            servicio.setName(key);

            Drawable image = imageView.getDrawable();
            image.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);
            imageView.setImageDrawable(image);

            servicio.setDrawable(image);

            servicioList.add(servicio);

            if (i < 4) {
                llDetailServices.addView(imageView);
            }

        }

        llDetailServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServicesDialog().showServices(getActivity(), servicioList);
            }
        });

        setTextViewDrawableColor(tvDetailDescriptionTitle);
        setTextViewDrawableColor(tvDetailUbicationTitle);

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

                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 18));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setPhotosNumber(int photosNumber) {
        tvDetailGallery.setText(String.valueOf(photosNumber));
    }

    @Override
    public void isLikeActive(boolean like) {
        if (like) {
            ivDetailLikes.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
            isDelete = true;
        } else {
            ivDetailLikes.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.darker_gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            isDelete = false;
        }
    }

    @Override
    public void setLikesNumber(int likesNumber) {
        tvLikes.setText(String.valueOf(likesNumber));

    }

    private void setTextViewDrawableColor(TextView textView) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(getActivity().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    private void saveLike() {

        Like like = new Like();

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        like.setFecha(input.format(new Date()));


        String timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + "";

        like.setTimeStamp(-1 * Double.parseDouble(timeStamp));

        like.setIdioma("Español");
        like.setLugar("PuertoMorelos");
        like.setNombreEntidad(subCategory.getNombre());

        Categorie c = ((Main_Activity) getActivity()).category;

        if (c.getCategoria() != null) {
            like.setSubcategoria(c.getName());
            like.setCategoria(c.getCategoria());
        } else
            like.setCategoria(c.getName());

        like.setItemKey(subCategory.getId());

        presenter.saveLike(like, isDelete);
    }


}
