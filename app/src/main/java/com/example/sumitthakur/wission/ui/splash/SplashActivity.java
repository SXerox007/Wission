package com.example.sumitthakur.wission.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.base.BaseActivity;
import com.example.sumitthakur.wission.ui.login.LoginActivity;
import com.example.sumitthakur.wission.util.Util;

public class SplashActivity extends BaseActivity {

    private static final int DELAY = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    /**
     * for initilization
     */
    private void init() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //open activity
                goToStartActivity();
            }
        }, DELAY);
    }


    /**
     * start a fresh activity
     */
    private void goToStartActivity() {
        Util.startFreshActivity(this, LoginActivity.class, null);
    }
}
