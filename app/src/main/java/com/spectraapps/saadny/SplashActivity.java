package com.spectraapps.saadny;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.spectraapps.saadny.util.ListSharedPreference;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends Activity {
    private boolean isFirstRun;

    ListSharedPreference.Set setSharedPreference;
    ListSharedPreference.Get getSharedPreference;

    @BindView(R.id.splach_screen)
    ImageView imageView;

    @BindView(R.id.btn_ar)
    Button button_ar;
    @BindView(R.id.btn_en)
    Button button_en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        ButterKnife.bind(SplashActivity.this);

        Picasso.get().load(R.drawable.app_logo).into(imageView);

        setSharedPreference = new ListSharedPreference.Set(SplashActivity.this.getApplicationContext());
        getSharedPreference = new ListSharedPreference.Get(SplashActivity.this.getApplicationContext());

        isFirstRun = getSharedPreference.getIsFirstRun();

        button_ar = findViewById(R.id.btn_ar);
        button_en = findViewById(R.id.btn_en);

        RelativeLayout relativeLayout = findViewById(R.id.splash_rela);
        relativeLayout.setBackgroundResource(R.color.backgroundColor);

        insertAnimation();
    }
    @OnClick(R.id.btn_en)
    protected void initButtonENClick() {
        setSharedPreference.setLanguage("en");
        setSharedPreference.setIsFirstRun(false);

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_ar)
    protected void initButtonARClick() {
        setSharedPreference.setLanguage("ar");
        setSharedPreference.setIsFirstRun(false);

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private void insertAnimation() {

        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_anim);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.ABSOLUTE);
        animation.setDuration(1300);

        ImageView splash = findViewById(R.id.splach_screen);
        splash.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isFirstRun) {
                    button_ar.setVisibility(View.VISIBLE);
                    button_en.setVisibility(View.VISIBLE);
                } else {
                    button_ar.setVisibility(View.INVISIBLE);
                    button_en.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isFirstRun) {
                    YoYo.with(Techniques.Bounce).playOn(button_ar);
                    YoYo.with(Techniques.Bounce).playOn(button_en);
                } else {
                    if (getSharedPreference.getIsLogged()) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }//end insert anim


}