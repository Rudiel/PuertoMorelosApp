package com.puertomorelosapp.puertomorelosapp.Main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.puertomorelosapp.puertomorelosapp.Fragments.Categories_Fragment;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Register.IRegister_Presenter;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((PuertoMorelosApplication) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);

        setmenuActions();

        setFragment(new Categories_Fragment(), false);

        presenter.getCategories();



    }


    private void setmenuActions() {
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        break;
                    case R.id.menu_activity:
                        break;
                    case R.id.menu_conditions:
                        break;
                    case R.id.menu_main:
                        break;
                    case R.id.menu_logout:
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

    private void setFragment(Fragment fragment, boolean isBackStack) {
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
    public void showCategories() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
