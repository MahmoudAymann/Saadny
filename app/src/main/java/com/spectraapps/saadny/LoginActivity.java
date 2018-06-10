package com.spectraapps.saadny;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.spectraapps.saadny.fragments.login.LoginFragment;
import com.spectraapps.saadny.interfaces.IOnBackPressed;
import com.spectraapps.saadny.util.ListSharedPreference;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private IOnBackPressed onBackPressedListener;
    ListSharedPreference.Get getSharedPref;
    ListSharedPreference.Set setSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSharedPref = new ListSharedPreference.Get(LoginActivity.this);
        setSharedPref = new ListSharedPreference.Set(LoginActivity.this);
        setLayoutLanguage();

        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction()
                .replace(R.id.login_frameLayout, new LoginFragment())
                .commit();
    }

    private void setLayoutLanguage() {
        String langStr = getSharedPref.getLanguage();
        Toast.makeText(this, ""+langStr, Toast.LENGTH_SHORT).show();
        Locale locale;
        if (langStr.equals("en")) {
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
    }

        @Override
        public void onBackPressed() {
            if (onBackPressedListener != null)
            onBackPressedListener.onBackPressed();
        else
            super.onBackPressed();
    }

    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }
}
