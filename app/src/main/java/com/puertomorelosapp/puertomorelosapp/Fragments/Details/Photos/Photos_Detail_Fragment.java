package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Gallery_Adapter;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Selfies_Adapter;
import com.puertomorelosapp.puertomorelosapp.Creators.AnonymousDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.Gallery_Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IAnonymousListener;
import com.puertomorelosapp.puertomorelosapp.Creators.INewPhoto_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.Loading_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.PhotoDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies.IDeleteSelfie;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Activity;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    @Bind(R.id.fabNew)
    FloatingActionMenu fabNew;

    @Inject
    IPhotos_Presenter presenter;

    private Main_Activity activity;

    int numberOfColumns = 3;
    int numberOfColumnsSelfie = 2;

    private List<Gallery> galleryList;

    private List<Selfie> selfieList;

    private final static int REQUEST_CAMERA = 0;
    private final static int REQUEST_GALERY = 1;

    private byte bytesThumb[];

    private Dialog loading;

    String mCurrentPhotoPath;

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
                if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                    } else {
                        cameraIntent();
                    }
                } else
                    new AnonymousDialog_Creator().showAnonymousDialog(
                            getActivity(),
                            getString(R.string.anonymous_title),
                            getString(R.string.anonymous_message_selfie),
                            new IAnonymousListener() {
                                @Override
                                public void onRegisterNow(Dialog dialog) {

                                    dialog.dismiss();
                                    getActivity().startActivity(new Intent(getActivity(), Register_Activity.class));
                                }

                                @Override
                                public void onRegisterLate(Dialog dialog) {
                                    dialog.dismiss();
                                }
                            }
                    ).show();
            }
        });

        fabGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALERY);
                    } else {
                        galleryIntent();
                    }
                } else
                    new AnonymousDialog_Creator().showAnonymousDialog(
                            getActivity(),
                            getString(R.string.anonymous_title),
                            getString(R.string.anonymous_message_selfie),
                            new IAnonymousListener() {
                                @Override
                                public void onRegisterNow(Dialog dialog) {

                                    dialog.dismiss();
                                    getActivity().startActivity(new Intent(getActivity(), Register_Activity.class));

                                }

                                @Override
                                public void onRegisterLate(Dialog dialog) {
                                    dialog.dismiss();
                                }
                            }
                    ).show();
            }
        });

        loading = new Loading_Creator().showLoadingLogin(getActivity(), getString(R.string.newselfie_loading));


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

        this.galleryList.clear();

        this.galleryList = galleryList;

        rvGallery.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        rvGallery.setAdapter(new Gallery_Adapter(galleryList, getActivity(), Photos_Detail_Fragment.this));
    }

    @Override
    public void showPhotos(List<Selfie> selfieList) {

        if (selfieList.size() == 0) {
            return;
        }

        this.selfieList.clear();

        this.selfieList = selfieList;

        Collections.sort(this.selfieList, new Comparator<Selfie>() {
            @Override
            public int compare(Selfie o1, Selfie o2) {
                return (o2.getFecha().compareTo(o1.getFecha()));
            }
        });

        rvSelfies.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumnsSelfie));

        rvSelfies.setAdapter(new Selfies_Adapter(selfieList, getActivity(), Photos_Detail_Fragment.this));


    }

    @Override
    public void showUploadingSelfie() {
        loading.show();
    }

    @Override
    public void hidelUploadingSelfie() {
        loading.dismiss();
    }


    @Override
    public void onGalleryClick(int position) {
        new Gallery_Dialog_Creator().showGallery(getActivity(), this.galleryList, position);
    }

    @Override
    public void onSlefieClick(int position) {
        new Gallery_Dialog_Creator().showSelfies(getActivity(), this.selfieList, position, false, new IDeleteSelfie() {
            @Override
            public void onDeleteSelfieListener(Selfie selfie) {
                //no hacemos nada aqui
            }
        });

    }

    private void cameraIntent() {
       /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);*/

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePicture.resolveActivity(getActivity().getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                Uri u = FileProvider.getUriForFile(getActivity(), "com.example.android.fileprovider", photoFile);

                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, u);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    takePicture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ClipData clip =
                            ClipData.newUri(getActivity().getContentResolver(), "A photo", u);

                    takePicture.setClipData(clip);
                    takePicture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    List<ResolveInfo> resInfoList =
                            getActivity().getPackageManager()
                                    .queryIntentActivities(takePicture, PackageManager.MATCH_DEFAULT_ONLY);

                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        getActivity().grantUriPermission(packageName, u,
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                }
                startActivityForResult(takePicture, REQUEST_CAMERA);
            }
        }
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
                onCaptureImageResult();
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
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        //loadImageIntoProfile(stream.toByteArray());

        ByteArrayOutputStream bytesT = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.JPEG, 80, bytesT);

        bytesThumb = bytesT.toByteArray();

        showNewPhotoDialog(stream.toByteArray());


    }


    private void onCaptureImageResult() {

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);

        ExifInterface ei = null;
        try {
            ei = new ExifInterface(mCurrentPhotoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmap;

        }


        ByteArrayOutputStream b = new ByteArrayOutputStream();
        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);

        ByteArrayOutputStream bytesT = new ByteArrayOutputStream();

        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytesT);

        bytesThumb = bytesT.toByteArray();

        showNewPhotoDialog(b.toByteArray());
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
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
            public void onConfirmClick(Dialog dialog, byte bytes[], Selfie selfie) {

                if (activity.category.getCategoria() == null) {
                    selfie.setCategoria(activity.category.getName());

                } else {
                    selfie.setCategoria(activity.category.getCategoria());
                    selfie.setSubcategoria(activity.category.getName());
                }

                selfie.setIdioma("Español");

                selfie.setLugar("PuertoMorelos");

                selfie.setItemKey(activity.subCategory.getId());

                selfie.setIdphotographer(Utils.getProvider(getActivity()));

                selfie.setNombreEntidad(activity.subCategory.getNombre());

                selfie.setTimeStamp((double) System.currentTimeMillis());

                presenter.saveSelfie(bytesThumb, bytes, selfie, activity.subCategory.getNombre());

                dialog.dismiss();
            }
        });
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PM_" + timeStamp + "_";
        File storageDire = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File i = File.createTempFile(imageFileName,
                ".jpg",
                storageDire);

        mCurrentPhotoPath = i.getAbsolutePath();
        return i;
    }


}
