package com.teamandroid.snapshare.ui.main.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.data.model.Post;

import java.net.URL;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder> {

    // TO DO: changes mData to Post[]
//    private Post[] mData;
    private String[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;


    public ProfilePostAdapter(Context context, String[] mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mContext = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.profile_post_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: Glide
        // holder.mImageView.setImageResource(mData[position].getImageUrl());
        ImageView imageView = holder.mImageView;
        String currentUrl = mData[position];

        Glide.with(mContext)
                .load(currentUrl)
                .fitCenter()
                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return mData.length;
    }

    public String getItem(int position) {
        return mData[position];
    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.profile_post_image);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }
}
