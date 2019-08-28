package com.teamandroid.snapshare.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.data.model.UserRegister;
import com.teamandroid.snapshare.databinding.ActivityRegisterBinding;
import com.teamandroid.snapshare.ui.main.MainActivity;
import com.teamandroid.snapshare.utils.Helper;

public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel mRegisterViewModel;
    private ActivityRegisterBinding mBinding;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        listenRegister();
    }

    private void init() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mBinding.setLifecycleOwner(this);
        mRegisterViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        mBinding.setRegisterViewModel(mRegisterViewModel);
        mProgressDialog = new ProgressDialog(this);
    }

    private void listenRegister() {
        mRegisterViewModel.getUserRegister().observe(this, new Observer<UserRegister>() {
            @Override
            public void onChanged(UserRegister userRegister) {
                if (!userRegister.isFilledAll())
                    Helper.showToast(getApplicationContext(),
                            getResources().getString(R.string.you_must_fill_all_information));
                else if (userRegister.isPasswordLessThanSixCharacter())
                    mBinding.editPassword
                            .setError(
                                    getResources().getString(R.string.password_must_more_than_6_char));
                else if (!userRegister.isPasswordSame())
                    mBinding.editRetypePassword
                            .setError(getResources().getString(R.string.password_must_be_same));
                else
                    mRegisterViewModel.register(userRegister);
            }
        });
        mRegisterViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    showProgressDialog();
                else
                    hideProgressDialog();
            }
        });
        mRegisterViewModel.getIsRegisterSuccessful().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    openMainActivity();
                else
                    Helper.showToast(getApplicationContext(),
                            getResources().getString(R.string.fail_try_again_later));
            }
        });
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getResources().getString(R.string.please_wait));
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    private void openMainActivity() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
