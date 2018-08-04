package com.example.sumitthakur.wission.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by sumitthakur on 25/07/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private BaseActivity baseActivity;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = this;

    }


    //show tost
    @Override
    public void showToast(final String text) {
        Toast.makeText(baseActivity, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showSnackBar(final String text, final View view) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}
