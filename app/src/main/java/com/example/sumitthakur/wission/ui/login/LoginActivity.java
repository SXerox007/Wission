package com.example.sumitthakur.wission.ui.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.base.BaseActivity;
import com.example.sumitthakur.wission.db.SecurePreferences;
import com.example.sumitthakur.wission.ui.home.HomeActivity;
import com.example.sumitthakur.wission.ui.home.HomeContollerActivity;
import com.example.sumitthakur.wission.util.Util;

import static com.example.sumitthakur.wission.Constants.AppConstants.PREF_PASSWORD;
import static com.example.sumitthakur.wission.Constants.AppConstants.PREF_USERNAME;
import static com.example.sumitthakur.wission.Constants.AppConstants.USER_INFO;
import static com.example.sumitthakur.wission.Constants.AppConstants.YOUR_SECURITY_KEY;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {

    private AppCompatEditText etEmailPhone, etPassword;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenterImpl(this);
        init();
    }

    private void init() {
        findViewById(R.id.ivBack).setVisibility(View.GONE);
        etEmailPhone = findViewById(R.id.etEmailPhone);
        etPassword = findViewById(R.id.etPassword);
        Util.setOnClickListener(this, findViewById(R.id.btnSignIn),
                findViewById(R.id.btnLoginFb), findViewById(R.id.tvForgotPassword), findViewById(R.id.tvCreate));
        fetchCredFromSecurePref();
    }


    private void saveCredSecurePreference() {
        SecurePreferences preferences = new SecurePreferences(this, USER_INFO,
                YOUR_SECURITY_KEY, true);
        preferences.put(PREF_USERNAME, etEmailPhone.getText().toString());
        preferences.put(PREF_PASSWORD, etPassword.getText().toString());
    }

    private void fetchCredFromSecurePref() {
        SecurePreferences preferences = new SecurePreferences(this, USER_INFO,
                YOUR_SECURITY_KEY, true);
        etEmailPhone.setText(preferences.getString(PREF_USERNAME));
        etPassword.setText(preferences.getString(PREF_PASSWORD));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                loginPresenter.onLoginClick(etEmailPhone.getText().toString(), etPassword.getText().toString());
                break;
            default:
                showToast(getString(R.string.label_dummy_label_not_in_used));
        }
    }

    @Override
    public void errMessageEmailPhoneIncorrect() {
        showToast(getString(R.string.err_email_phone_incorrect));
    }

    @Override
    public void errorPassword() {
        showToast(getString(R.string.err_password));
    }

    @Override
    public void onLoginSucess() {
        saveCredSecurePreference();
        Util.startFreshActivity(this, HomeContollerActivity.class, null);
    }

    @Override
    public void errorEmptyEmailPhone() {
        showToast(getString(R.string.email_phone_required));
    }

    @Override
    public void errorEmptyPassword() {
        showToast(getString(R.string.password_required));
    }
}
