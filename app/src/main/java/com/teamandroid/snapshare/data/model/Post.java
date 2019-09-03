package com.teamandroid.snapshare.data.model;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.PropertyName;
import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.utils.Helper;

public class Post {
    public static final String POST_COLLECTION = "Posts";
    public static final String FIELD_AUTHOR = "author";
    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_AVATAR_URL = "avatarUrl";
    public static final String FIELD_IMAGE_URL = "imageUrl";
    public static final String FIELD_CAPTION = "caption";
    public static final String FIELD_LIKE_COUNT = "likeCount";
    public static final String FIELD_CREATED_AT = "createdAt";
    @PropertyName(FIELD_AUTHOR)
    private String mAuthor;
    @PropertyName(FIELD_USER_ID)
    private String mUserId;
    @PropertyName(FIELD_AVATAR_URL)
    private String mAvatarUrl;
    @PropertyName(FIELD_IMAGE_URL)
    private String mImageUrl;
    @PropertyName(FIELD_CAPTION)
    private String mCaption;
    @PropertyName(FIELD_LIKE_COUNT)
    private Integer mLikeCount;
    @PropertyName(FIELD_CREATED_AT)
    private Timestamp mCreatedAt;

    public Post() {
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCaption() {
        return mCaption;
    }

    public Integer getLikeCount() {
        return mLikeCount;
    }

    public Timestamp getCreatedAt() {
        return mCreatedAt;
    }

    @BindingAdapter({"likeCount"})
    public static void loadLikeCounter(TextView textView, Integer likeCount) {
            textView.setText(String.format(textView.getContext().getString(R.string.like_counter),
                    likeCount, likeCount > 1 ? "s" : ""));
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImagePost(ImageView imageView, String imageUrl) {
        Glide.with(imageView).load(imageUrl).into(imageView);
    }

    @BindingAdapter({"avatarUrl"})
    public static void loadAvatar(ImageView imageView, String imageUrl) {
        Glide.with(imageView).load(imageUrl).into(imageView);
    }

    @BindingAdapter({"createdAt"})
    public static void loadTimeCreated(TextView textView, Timestamp createdAt) {
        textView.setText(Helper.getDateFromUnixTime(createdAt));
    }
}
