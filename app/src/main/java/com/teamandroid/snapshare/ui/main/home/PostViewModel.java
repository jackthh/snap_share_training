package com.teamandroid.snapshare.ui.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.teamandroid.snapshare.data.model.Post;
import com.teamandroid.snapshare.data.repository.FirestoreRepository;

import java.util.List;

public class PostViewModel extends ViewModel {
    private MutableLiveData<List<Post>> mPosts;
    private FirestoreRepository mFirestoreRepository = FirestoreRepository.getInstance();

    public LiveData<List<Post>> getPosts() {
        if (mPosts == null) {
            mPosts = new MutableLiveData<>();
            loadPosts();
        }
        return mPosts;
    }

    private void loadPosts() {
        mFirestoreRepository.getAllPosts(new FirestoreRepository.Callback<List<Post>>() {
            @Override
            public void onSuccess(List<Post> result) {
                mPosts.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                //TODO handle when fetching fail
            }
        });
    }

}

