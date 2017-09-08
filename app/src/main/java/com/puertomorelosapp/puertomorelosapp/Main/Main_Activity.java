package com.puertomorelosapp.puertomorelosapp.Main;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.puertomorelosapp.puertomorelosapp.Creators.ConfirmDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IConfirmDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs.AboutUs_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activity_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.Categories_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Conditions_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Map.Fragment_Map;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main_Activity extends AppCompatActivity implements IMain_View {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Bind(R.id.dlMain)
    DrawerLayout drawerLayout;

    @Bind(R.id.nvMain)
    NavigationView navigationView;

    @Inject
    IMain_Presenter presenter;

    @Bind(R.id.ivAd)
    ImageView ivAd;

    private TextView titleToolbar;

    public FirebaseAuth auth;

    public ImageView ivMap;

    public List<Categorie> categorieList;
    public List<SubCategory> subCategoryList;
    public List<Categorie> thirdCategoryList;

    @Bind(R.id.pbMain)
    ProgressBar pbMain;

    public Categorie category;
    public SubCategory subCategory;


    public String mainCategory = "";

    ActionBarDrawerToggle actionBarDrawerToggle;

    private TextView tvMenuComments;

    private TextView tvMenuLikes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        ((PuertoMorelosApplication) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);

        presenter.getAd();

        setmenuActions();

        ivMap = (ImageView) toolbar.findViewById(R.id.ivMap);

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new Fragment_Map(), false, null);
                setToolbarTitle(getString(R.string.app_name));
            }
        });

        titleToolbar = (TextView) toolbar.findViewById(R.id.tvTitleToolbar);

        titleToolbar.setTypeface(Utils.getbukharisLetter(this));

        categorieList = new ArrayList<>();
        thirdCategoryList = new ArrayList<>();
        subCategoryList = new ArrayList<>();


        setFragment(new Categories_Fragment(), false, null);

        pbMain.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        showMenu();

        tvMenuComments = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvMenuComments);

        presenter.getMenuComments(Utils.getProvider(this));

        presenter.getMenuLikes(Utils.getProvider(this));

    }


    private void setmenuActions() {
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        setFragment(new AboutUs_Fragment(), false, null);
                        drawerLayout.closeDrawers();
                        setToolbarTitle(getString(R.string.menu_about));
                        break;
                    case R.id.menu_activity:
                        setFragment(new Activity_Fragment(), false, null);
                        drawerLayout.closeDrawers();
                        setToolbarTitle(getString(R.string.menu_myactivity));
                        break;
                    case R.id.menu_conditions:
                        setFragment(new Conditions_Fragment(), false, null);
                        drawerLayout.closeDrawers();
                        setToolbarTitle(getString(R.string.menu_conditions));
                        break;
                    case R.id.menu_main:
                        setFragment(new Categories_Fragment(), false, null);
                        drawerLayout.closeDrawers();
                        setToolbarTitle(getString(R.string.menu_main));
                        break;
                    case R.id.menu_logout:
                        presenter.logout(auth);
                        break;
                }

                return false;
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    public void setFragment(Fragment fragment, boolean isBackStack, @Nullable View image) {
        if (isBackStack) {
            if (image != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContainer, fragment)
                        .addSharedElement(image, ViewCompat.getTransitionName(image))
                        .addToBackStack(null)
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }

        } else {
            if (image != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContainer, fragment)
                        .addSharedElement(image, ViewCompat.getTransitionName(image))
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContainer, fragment)
                        .commit();
            }
        }
    }

    @Override
    public void showLoading() {
        pbMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbMain.setVisibility(View.GONE);
    }

    @Override
    public void logoutSesion() {

        drawerLayout.closeDrawers();

        new ConfirmDialog_Creator().showConfirmDialog(this,
                getString(R.string.menu_logout),
                getString(R.string.menu_close_session_message),
                new IConfirmDialog_Creator() {
                    @Override
                    public void onAccept(AlertDialog dialog) {

                        auth.signOut();

                        dialog.dismiss();

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Main_Activity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();

                        LoginManager.getInstance().logOut();

                        startActivity(new Intent(Main_Activity.this, Login_Activity.class));

                        finish();
                    }

                    @Override
                    public void onCancel(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                });


    }

    @Override
    public void setComments(int comments) {
        tvMenuComments.setText(String.valueOf(comments));
    }

    @Override
    public void showAd(String URL) {

        toolbar.setVisibility(View.GONE);

        ivAd.setVisibility(View.VISIBLE);


        Glide.with(this).load(URL).centerCrop().into(ivAd);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toolbar.setVisibility(View.VISIBLE);
                ivAd.setVisibility(View.GONE);
            }
        }, 3000);
    }


    public void setToolbarTitle(String title) {
        titleToolbar.setText(title);
    }

    public void hideMenu() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        titleToolbar.setPadding(0, 0, 0, 0);
    }

    public void showMenu() {

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        titleToolbar.setPadding(0, 0, (int) getResources().getDimension(R.dimen.margin_titile), 0);
        actionBarDrawerToggle.syncState();
    }


}
