package com.teamandroid.snapshare.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;
import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.databinding.ActivityLogInBinding;
import com.teamandroid.snapshare.ui.main.MainActivity;
import com.teamandroid.snapshare.utils.Constants;

public class LogInActivity extends AppCompatActivity {
    private LoginViewModel mLoginViewModel;
    private ActivityLogInBinding mBinding;
    private GoogleSignInClient mGoogleSignInClient;

    // Configure Google Sign In
    private GoogleSignInOptions mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();
        listenLogin();
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        mLoginViewModel.checkSigned(account);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RC_SIGN_IN) {
            mLoginViewModel.googleSignIn(data);
        }
    }


    private void init() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        mBinding.setLifecycleOwner(this);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mBinding.setLoginViewModel(mLoginViewModel);
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions);
    }


    private void listenLogin() {
        mLoginViewModel.getIsSigned().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSigned) {
                if (isSigned != true) {
                    RelativeLayout loginScreen = findViewById(R.id.relative_login);
                    Snackbar.make(loginScreen, R.string.login_necessity_description, Snackbar.LENGTH_SHORT).show();
                } else {
                    launchMainActivity();
                }
            }
        });
    }


    public void onLoginClicked() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    public void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
