package com.teamandroid.snapshare.data.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.teamandroid.snapshare.data.model.Post;

import java.util.ArrayList;
import java.util.List;

public class FirestoreRepository {
    public static FirestoreRepository sInstance;
    private final FirebaseFirestore mFirestore;

    private FirestoreRepository() {
        mFirestore = FirebaseFirestore.getInstance();
    }

    public static FirestoreRepository getInstance() {
        if (sInstance == null) {
            synchronized (FirestoreRepository.class) {
                if (sInstance == null) {
                    sInstance = new FirestoreRepository();
                }
            }
        }
        return sInstance;
    }

    public void getAllPosts(final Callback<List<Post>> callback) {

        mFirestore.collection(Post.POST_COLLECTION).orderBy(Post.FIELD_CREATED_AT).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Post> posts = new ArrayList<>();
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            Post post = document.toObject(Post.class);
                            posts.add(post);
                        }
                        callback.onSuccess(posts);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure(e);
                    }
                });
    }

    public interface Callback<T> {

        void onSuccess(T result);
        
        void onFailure(Exception e);
    }
}
