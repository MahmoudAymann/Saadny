package com.spectraapps.saadny.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ListSharedPreference {

    public static class Set {
        Context context;

        public Set(Context context) {
            this.context = context;
        }

        public void setIsFirstRun(boolean isFirstRun) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putBoolean("isFirstRun", isFirstRun).apply();
        }

        public void setLanguage(String lang) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("lang", lang).apply();
        }

        public void setIsLogged(boolean isLoggedIn) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putBoolean("islogin", isLoggedIn).apply();
        }

        public void setUId(String id) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("uid", id).apply();
        }

        public void setUName(String name) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("uname", name).apply();
        }

    }//end Set


    public static class Get {
        Context context;

        public Get(Context context) {
            this.context = context;
        }

        public boolean getIsFirstRun() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getBoolean("isFirstRun", true);
        }

        public String getLanguage() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("lang", "ar");
        }

        public boolean getIsLogged() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getBoolean("islogin", false);
        }

        public String getUId() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("uid", "id");
        }

        public String getUName() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("uname", "agent");
        }
    }
}//end ListSharedPreference

