package com.teamandroid.snapshare.data.model;

import android.text.TextUtils;

public class UserRegister {
    private String mUsername;
    private String mFullName;
    private String mEmail;
    private String mPassword;
    private String mConfirmPassword;

    public UserRegister(String mUsername, String mFullName, String mEmail, String mPassword,
                        String mConfirmPassword) {
        this.mUsername = mUsername;
        this.mFullName = mFullName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mConfirmPassword = mConfirmPassword;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getConfirmPassword() {
        return mConfirmPassword;
    }

    public void setConfirmPassword(String mConfirmPassword) {
        this.mConfirmPassword = mConfirmPassword;
    }

    public boolean isFilledAll() {
        return !TextUtils.isEmpty(mUsername) && !TextUtils.isEmpty(mFullName)
            && !TextUtils.isEmpty(mPassword) && !TextUtils.isEmpty(mConfirmPassword)
            && !TextUtils.isEmpty(mEmail);
    }

    public boolean isPasswordLessThanSixCharacter() {
        return mPassword != null && mPassword.length() < 6;
    }

    public boolean isPasswordSame() {
        return mPassword != null && mPassword.equals(mConfirmPassword);
    }
}