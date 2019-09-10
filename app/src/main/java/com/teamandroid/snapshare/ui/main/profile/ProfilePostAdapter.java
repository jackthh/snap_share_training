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

import java.util.ArrayList;
import java.util.List;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder> {

    private List<Post> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;


    public ProfilePostAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.profile_main_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: Glide
        ImageView imageView = holder.mImageView;
        String currentUrl = mData.get(position).getImageUrl();

        Glide.with(mContext)
                .load(currentUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher)
                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public void setData(List<Post> posts) {
        this.mData = posts;
        notifyDataSetChanged();
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
