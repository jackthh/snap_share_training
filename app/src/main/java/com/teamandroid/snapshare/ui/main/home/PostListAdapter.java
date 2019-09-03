package com.teamandroid.snapshare.ui.main.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.data.model.Post;
import com.teamandroid.snapshare.databinding.PostItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {
    private List<Post> mPosts = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostItemBinding postItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.post_item, parent, false);
        return new PostViewHolder(postItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.mPostItemBinding.setPost(mPosts.get(position));
    }

    void setPostList(List<Post> posts) {
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mPosts != null) ? mPosts.size() : 0;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private PostItemBinding mPostItemBinding;

        public PostViewHolder(@NonNull PostItemBinding postItemBinding) {
            super(postItemBinding.getRoot());
            this.mPostItemBinding = postItemBinding;
        }
    }
}
