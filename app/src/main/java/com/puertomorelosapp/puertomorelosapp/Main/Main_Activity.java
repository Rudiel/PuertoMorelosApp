package com.puertomorelosapp.puertomorelosapp.Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs.AboutUs_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activity_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.Categories_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Conditions_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Map.Fragment_Map;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
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

    private TextView titleToolbar;

    private FirebaseAuth auth;

    public ImageView ivMap;

    public List<Categorie> categorieList;

    @Bind(R.id.pbMain)
    ProgressBar pbMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        ((PuertoMorelosApplication) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);

        setmenuActions();

        ivMap = (ImageView) toolbar.findViewById(R.id.ivMap);

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new Fragment_Map(), false);
                setToolbarTitle(getString(R.string.app_name));
            }
        });

        titleToolbar = (TextView) toolbar.findViewById(R.id.tvTitleToolbar);

        titleToolbar.setTypeface(Utils.getbukharisLetter(this));

        categorieList = new ArrayList<>();

        setToolbarTitle(getString(R.string.menu_main));

        setFragment(new Categories_Fragment(), false);

    }


    private void setmenuActions() {
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        setFragment(new AboutUs_Fragment(), false);
                        drawerLayout.closeDrawers();
                        setToolbarTitle(getString(R.string.menu_about));
                        break;
                    case R.id.menu_activity:
                        setFragment(new Activity_Fragment(), false);
                        drawerLayout.closeDrawers();
                        setToolbarTitle(getString(R.string.menu_myactivity));
                        break;
                    case R.id.menu_conditions:
                        setFragment(new Conditions_Fragment(), false);
                        drawerLayout.closeDrawers();
                        setToolbarTitle(getString(R.string.menu_conditions));
                        break;
                    case R.id.menu_main:
                        setFragment(new Categories_Fragment(), false);
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

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
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

    public void setFragment(Fragment fragment, boolean isBackStack) {
        if (isBackStack)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        else
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, fragment)
                    .commit();
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
        LoginManager.getInstance().logOut();
        startActivity(new Intent(Main_Activity.this, Login_Activity.class));
        finish();
    }

    public void setToolbarTitle(String title) {
        titleToolbar.setText(title);
    }


}
