package com.teamandroid.snapshare.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.teamandroid.snapshare.utils.Constants;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> mIsSigned = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsSigned() {
        return mIsSigned;
    }

    public void googleSignIn(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            mIsSigned.postValue(true);
            // For later use
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
        } catch (ApiException e) {
            Log.w(Constants.GOOGLE_ACTIVITY_TAG, String.valueOf(e.getStatusCode()));
        }
    }


    public void checkSigned(GoogleSignInAccount account) {
        mIsSigned.postValue((account != null) ? true : false);
    }

}
