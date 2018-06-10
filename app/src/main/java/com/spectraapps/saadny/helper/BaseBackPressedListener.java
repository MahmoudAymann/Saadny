package com.spectraapps.saadny.helper;


import android.app.Activity;
import android.app.FragmentManager;

import com.spectraapps.saadny.interfaces.IOnBackPressed;

/**
 * Created by MahmoudAyman on 10/02/2018.
 */

public class BaseBackPressedListener implements IOnBackPressed {
    private final Activity activity;

    protected BaseBackPressedListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onBackPressed() {
        activity.getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}