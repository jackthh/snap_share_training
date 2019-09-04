package com.teamandroid.snapshare.ui.main.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.data.model.User;
import com.teamandroid.snapshare.databinding.UserItemBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private List<User> mUsers = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding userItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.user_item, parent, false);
        return new ViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mUserItemBinding.setUser(mUsers.get(position));
    }

    public void setUserList(List<User> users) {
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private UserItemBinding mUserItemBinding;

        public ViewHolder(@NonNull UserItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mUserItemBinding = itemBinding;
        }
    }

}
