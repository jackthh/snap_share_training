package com.teamandroid.snapshare.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.databinding.ActivityLoginBinding;
import com.teamandroid.snapshare.ui.main.MainActivity;
import com.teamandroid.snapshare.ui.register.RegisterActivity;
import com.teamandroid.snapshare.utils.CommonUtils;
import com.teamandroid.snapshare.utils.Helper;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mBinding;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        listenLogin();
    }

    private void init() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mBinding.setLifecycleOwner(this);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mBinding.setLoginViewModel(mLoginViewModel);
    }

    private void listenLogin() {
        mLoginViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showProgressDialog();
                } else {
                    hideProgressDialog();
                }
            }
        });
        mLoginViewModel.getIsInputValid().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isInputValid) {
                if (!isInputValid) {
                    Helper.showToast(getApplicationContext(), getString(R.string.invalid_email_password));
                }
            }
        });
        mLoginViewModel.getIsSignInSuccessful().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccessful) {
                if (isSuccessful) {
                    launchMainActivity();
                } else {
                    Helper.showToast(getApplicationContext(), getString(R.string.incorrect_email_password));
                }
            }
        });
    }

    public void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressDialog() {
        mProgressDialog = CommonUtils.createLoadingDialog(this);
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void launchRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
