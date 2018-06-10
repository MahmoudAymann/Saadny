package com.spectraapps.saadny;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.spectraapps.saadny.LoginActivity;
import com.spectraapps.saadny.R;
import com.spectraapps.saadny.fragments.main.GuideFragment;
import com.spectraapps.saadny.fragments.main.HomeFragment;
import com.spectraapps.saadny.fragments.main.NotificationFragment;
import com.spectraapps.saadny.fragments.main.ProfileFragment;
import com.spectraapps.saadny.interfaces.IOnBackPressed;
import com.spectraapps.saadny.interfaces.MainViewsCallBack;
import com.spectraapps.saadny.util.ListSharedPreference;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainViewsCallBack{

    ImageButton toolbarFilterButton;
    TextView toolbarTitle;
    private Locale locale;

    AlertDialog.Builder alertDialogBuilder;

    ListSharedPreference.Set setSharedPreference;
    ListSharedPreference.Get getSharedPreference;

    protected IOnBackPressed onBackPressedListener;

    private boolean mIsLogged;
    private DrawerLayout mDrawer;
    public Toolbar mToolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSharedPreference = new ListSharedPreference.Set(MainActivity.this.getApplicationContext());
        getSharedPreference = new ListSharedPreference.Get(MainActivity.this.getApplicationContext());
        getSharedPrefData();
        setLAyoutLanguage();

        mToolbar = findViewById(R.id.main_toolbar);

        initNavigationDrawer();

        initBottomTabBar();
        initUI();
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frameLayout, new HomeFragment()).commit();
    }

    private void getSharedPrefData() {
        mIsLogged = getSharedPreference.getIsLogged();
    }

    private void initUI() {
        toolbarTitle = findViewById(R.id.toolbar_title);
    }

    private void initNavigationDrawer() {
        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initBottomTabBar() {

        final String[] colors = getResources().getStringArray(R.array.tab_bar_colors);

        NavigationTabBar navigationTabBar = findViewById(R.id.ntb_horizontal);


        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        if (getLang().equals("en"))
            initModelsInEnglish(models, colors);
        else
            initModelsInArabic(models, colors);
        navigationTabBar.setModels(models);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(NavigationTabBar.Model model, int index) {
                if (getLang().equals("en"))
                    beginFragmentTransactionsEnglish(index);
                else
                    beginFragmentTransactionsArabic(index);
            }

            @Override
            public void onEndTabSelected(NavigationTabBar.Model model, int index) {
                //addToolbarTitleAndIcons(index);
            }
        });

        //background color
        navigationTabBar.setBgColor(Color.parseColor(colors[1]));

    }//end initUi

    private void initModelsInEnglish(ArrayList<NavigationTabBar.Model> models, String[] colors) {
        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this,
                                R.drawable.ic_home_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this, R.drawable.ic_notification_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this, R.drawable.ic_call_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this, R.drawable.ic_profile_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );
    }

    private void initModelsInArabic(ArrayList<NavigationTabBar.Model> models, String[] colors) {
        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this, R.drawable.ic_profile_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this, R.drawable.ic_call_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this, R.drawable.ic_notification_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        AppCompatResources.getDrawable(MainActivity.this, R.drawable.ic_home_24dp),
                        Color.parseColor(colors[2]))
                        .build()
        );
    }

    private void beginFragmentTransactionsEnglish(int index) {
        switch (index) {
            case 0:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new HomeFragment()).commit();
                break;
            case 1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new NotificationFragment()).commit();
                break;
            case 2:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new GuideFragment()).commit();
                break;
            case 3:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new ProfileFragment()).commit();
                break;
            default:
                break;
        }//end switch
    }

    private void beginFragmentTransactionsArabic(int index) {
        switch (index) {
            case 0:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new ProfileFragment()).commit();
                break;
            case 1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new GuideFragment()).commit();
                break;
            case 2:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new NotificationFragment()).commit();
                break;
            case 3:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frameLayout, new HomeFragment()).commit();
                break;
            default:
                break;
        }//end switch
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.onBackPressed();
        else
            super.onBackPressed();
    }
    public static void restartActivity(Activity activity) {
        activity.recreate();
    }
    private void setLAyoutLanguage() {
        if (getLang().equals("en")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            this.getApplicationContext().getResources().updateConfiguration(config, null);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            this.getApplicationContext().getResources().updateConfiguration(config, null);
        }

        this.setContentView(R.layout.activity_main);
        NavigationView navigationView = this.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        if (id == R.id.profile_nav) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.main_frameLayout, new PatientProfileFragment()).commit();
//        } else if (id == R.id.requests_nav) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.main_frameLayout, new RequestsFragment()).commit();
//        } else if (id == R.id.notifications_nav) {
//
//        } else if (id == R.id.adding_items_nav) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.main_frameLayout, new AddingItemsFragment()).commit();
//        } else if (id == R.id.favourite_nav) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.main_frameLayout, new FavouritesFragment()).commit();
//        } else if (id == R.id.cart_nav) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.main_frameLayout, new CartFragment()).commit();
//        } else if (id == R.id.balance_nav) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.main_frameLayout, new BalanceFragment()).commit();
//        }else if (id == R.id.reports_nav) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.main_frameLayout, new ReprortsFragment()).commit();
//        } else if (id == R.id.language_nav) {
//            setAlertDialog(1);
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//        } else if (id == R.id.nav_faq) {
//
//        } else if (id == R.id.nav_privacy_policy) {
//
//        } else if (id == R.id.logout_nav) {
//            Intent i = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(i);
//        }else if (id == R.id.nav_about) {
//            Intent i = new Intent(MainActivity.this, AboutActivity.class);
//            startActivity(i);
//        }

        mDrawer = findViewById(R.id.drawer_layout);
        mDrawer.closeDrawer(Gravity.START);

        return true;
    }

    private void setAlertDialog(int i) {
        switch (i) {
            case 1:
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage(getString(R.string.change_language_prompt));

                alertDialogBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (getSharedPreference.getLanguage().equals("en")) {
                            setSharedPreference.setLanguage("ar");
                            restartActivity(MainActivity.this);
                        } else {
                            setSharedPreference.setLanguage("en");
                            restartActivity(MainActivity.this);
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }

    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the locale has changed
        if (!locale.equals(newConfig.locale)) {
            locale = newConfig.locale;

            this.setContentView(R.layout.activity_main);
            NavigationView navigationView = this.findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(MainActivity.this);
        }
    }

    @Override
    public void toolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void toolbarBtn(boolean visibility) {

    }

    private String getLang() {
        return getSharedPreference.getLanguage();
    }

}

//new AlertDialog.Builder(getActivity())
//        .setTitle(R.string.connection_dialog_title)
//        .setMessage(title)
//        .setCancelable(false)
//        .setPositiveButton(R.string.connection_dialog_positive_title, null)
//        .create().show();