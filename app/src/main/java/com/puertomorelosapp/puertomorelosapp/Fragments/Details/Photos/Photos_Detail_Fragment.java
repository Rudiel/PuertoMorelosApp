package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Gallery_Adapter;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Selfies_Adapter;
import com.puertomorelosapp.puertomorelosapp.Creators.Gallery_Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.INewPhoto_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.PhotoDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Photos_Detail_Fragment extends Fragment implements IPhotos_View, IGalleryClickListener, ISelfieClickListener {

    @Bind(R.id.rvSelfies)
    RecyclerView rvSelfies;

    @Bind(R.id.rvGallery)
    RecyclerView rvGallery;

    @Bind(R.id.tvPhotosGalleryTitle)
    TextView tvPhotosGalleryTitle;

    @Bind(R.id.tvPhotosSelfiesTitle)
    TextView tvPhotosSelfiesTitle;

    @Bind(R.id.llPhotosGallery)
    RelativeLayout llPhotosGallery;

    @Bind(R.id.llPhotosSelfies)
    RelativeLayout llPhotosSelfies;

    @Bind(R.id.pbGallery)
    ProgressBar pbGallery;

    @Bind(R.id.pbSelfies)
    ProgressBar pbSelfies;

    @Bind(R.id.fabGallery)
    FloatingActionButton fabGallery;

    @Bind(R.id.fabCamera)
    FloatingActionButton fabCamera;

    @Inject
    IPhotos_Presenter presenter;

    private Main_Activity activity;

    int numberOfColumns = 3;

    private List<Gallery> galleryList;

    private List<Selfie> selfieList;

    private final static int REQUEST_CAMERA = 0;
    private final static int REQUEST_GALERY = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pictures, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this, view);

        presenter.setView(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = ((Main_Activity) getActivity());

        tvPhotosGalleryTitle.setTypeface(Utils.getbukharisLetter(getActivity()));
        tvPhotosSelfiesTitle.setTypeface(Utils.getbukharisLetter(getActivity()));

        galleryList = new ArrayList<>();

        selfieList = new ArrayList<>();

        presenter.getGallery(activity.category, activity.subCategory);

        presenter.getSelfies(activity.category, activity.subCategory);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                } else {
                    cameraIntent();
                }
            }
        });

        fabGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALERY);
                } else {
                    galleryIntent();
                }
            }
        });
    }


    @Override
    public void showLoadingSelfie() {
        pbSelfies.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingSelfie() {
        pbSelfies.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingGallery() {
        pbGallery.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingGallery() {
        pbGallery.setVisibility(View.GONE);
    }

    @Override
    public void showGallery(List<Gallery> galleryList) {

        if (galleryList.size() > 0)
            llPhotosGallery.setVisibility(View.VISIBLE);

        this.galleryList = galleryList;

        rvGallery.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        rvGallery.setAdapter(new Gallery_Adapter(galleryList, getActivity(), Photos_Detail_Fragment.this));
    }

    @Override
    public void showPhotos(List<Selfie> selfieList) {

        this.selfieList = selfieList;

        rvSelfies.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        rvSelfies.setAdapter(new Selfies_Adapter(selfieList, getActivity(), Photos_Detail_Fragment.this));


    }

    @Override
    public void onGalleryClick(int position) {
        new Gallery_Dialog_Creator().showGallery(getActivity(), this.galleryList, position);
    }

    @Override
    public void onSlefieClick(int position) {

    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_GALERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALERY) {
                onSelectFromGalleryResult(data);
            }
            if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 90, stream);

        //loadImageIntoProfile(stream.toByteArray());

        showNewPhotoDialog(stream.toByteArray());

    }


    private void onCaptureImageResult(Intent data) {
        if (data != null) {


            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            showNewPhotoDialog(bytes.toByteArray());

            //loadImageIntoProfile(bytes.toByteArray());

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Permiso concedido
                cameraIntent();
            }
        }
        if (requestCode == REQUEST_GALERY) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Permiso concedido
                galleryIntent();
            }
        }
    }

    private void showNewPhotoDialog(byte[] bytes) {

        new PhotoDialog_Creator().showNewPhotoDialog(getActivity(), bytes, new INewPhoto_Creator() {
            @Override
            public void onCancelClick(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onConfirmClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
    }
}
