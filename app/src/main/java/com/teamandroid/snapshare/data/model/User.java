package com.teamandroid.snapshare.data.model;

public class User {
    public static final String COLLECTION = "Users";
    public static final String FIELD_ID = "id";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_FULL_NAME = "full_name";
    public static final String FIELD_AVATAR_URL = "avatar_url";
    private String mId;
    private String mUsername;
    private String mFullName;
    private String mAvatarUrl;

    public User() {
    }

    public User(String id, String username, String fullName, String avatarUrl) {
        this.mId = id;
        this.mUsername = username;
        this.mFullName = fullName;
        this.mAvatarUrl = avatarUrl;
    }

    public User(String username, String fullName, String avatarUrl) {
        this.mUsername = username;
        this.mFullName = fullName;
        this.mAvatarUrl = avatarUrl;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.mAvatarUrl = avatarUrl;
    }
}
