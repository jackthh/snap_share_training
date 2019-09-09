package com.teamandroid.snapshare.ui.login;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamandroid.snapshare.utils.CommonUtils;

public class LoginViewModel extends ViewModel {
    public static final String TAG = LoginViewModel.class.getSimpleName();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsInputValid = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsSignInSuccessful = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsInputValid() {
        return mIsInputValid;
    }

    public MutableLiveData<Boolean> getIsSignInSuccessful() {
        return mIsSignInSuccessful;
    }

    public void doSignIn(String email, String password) {
        mIsLoading.postValue(true);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            mIsSignInSuccessful.postValue(true);
                        } else {
                            mIsSignInSuccessful.postValue(false);
                        }
                        mIsLoading.postValue(false);
                    }
                });
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        return (CommonUtils.isEmailValid(email) && !TextUtils.isEmpty(password));
    }

    public void onLoginClicked(View v) {
        String email = this.email.getValue();
        String password = this.password.getValue();
        if (!isEmailAndPasswordValid(email, password)) {
            mIsInputValid.setValue(false);
        } else {
            doSignIn(this.email.getValue(), this.password.getValue());
        }
    }
}
