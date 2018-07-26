package com.example.sumitthakur.wission.ui.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;

import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.base.BaseActivity;
import com.example.sumitthakur.wission.db.SecurePreferences;

import static com.example.sumitthakur.wission.Constants.AppConstants.PREF_PASSWORD;
import static com.example.sumitthakur.wission.Constants.AppConstants.PREF_USERNAME;
import static com.example.sumitthakur.wission.Constants.AppConstants.USER_INFO;
import static com.example.sumitthakur.wission.Constants.AppConstants.YOUR_SECURITY_KEY;

public class LoginActivity extends BaseActivity {

    private AppCompatEditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    private void saveCredSecurePreference() {
        SecurePreferences preferences = new SecurePreferences(this, USER_INFO,
                YOUR_SECURITY_KEY, true);
        preferences.put(PREF_USERNAME, etEmail.getText().toString());
        preferences.put(PREF_PASSWORD, etPassword.getText().toString());
    }

    private void fetchCredFromSecurePref() {
        SecurePreferences preferences = new SecurePreferences(this, USER_INFO,
                YOUR_SECURITY_KEY, true);
        etEmail.setText(preferences.getString(PREF_USERNAME));
        etPassword.setText(preferences.getString(PREF_PASSWORD));
    }
}
