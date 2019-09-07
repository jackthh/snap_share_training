package com.teamandroid.snapshare.ui.main.profile;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.teamandroid.snapshare.R;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class ProfileFragment extends Fragment implements ProfilePostAdapter.ItemClickListener{
    private final int colNumb = 3;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProfilePostAdapter mProfilePostAdapter;
    private ProfileViewModel mProfileViewModel;

    // Test w/ hard link
    private String[] mData = {"https://cdn.dribbble.com/users/759223/screenshots/2568965/dota2_400x300.png",
            "https://icon-library.net/images/dota-2-icon/dota-2-icon-0.jpg",
            "https://bacdau.vn/wp-content/uploads/2019/05/icon-dota-2.png",
            "http://i.imgur.com/rlx1Kb2.png",
            "https://cdn.dribbble.com/users/759223/screenshots/2568965/dota2_400x300.png",
            "https://icon-library.net/images/dota-2-icon/dota-2-icon-0.jpg",
            "https://bacdau.vn/wp-content/uploads/2019/05/icon-dota-2.png",
            "http://i.imgur.com/rlx1Kb2.png",
            "https://cdn.dribbble.com/users/759223/screenshots/2568965/dota2_400x300.png",
            "https://icon-library.net/images/dota-2-icon/dota-2-icon-0.jpg",
            "https://bacdau.vn/wp-content/uploads/2019/05/icon-dota-2.png",
            "http://i.imgur.com/rlx1Kb2.png",
            "https://cdn.dribbble.com/users/759223/screenshots/2568965/dota2_400x300.png",
            "https://icon-library.net/images/dota-2-icon/dota-2-icon-0.jpg",
            "https://bacdau.vn/wp-content/uploads/2019/05/icon-dota-2.png",
            "http://i.imgur.com/rlx1Kb2.png"
    };

    public ProfileFragment() {
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        mToolbar = rootView.findViewById(R.id.toolbar);
        setToolbar();

        // Prepare for glide
        ListPreloader.PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
        ListPreloader.PreloadModelProvider preloadModelProvider = new CustomPreloadModelProvider();
        RecyclerViewPreloader<Image> preloader = new RecyclerViewPreloader(
                getActivity(), preloadModelProvider, sizeProvider, 6);
        Log.i("RECYCLER PRELOADER", "GOT INITIALIZED");
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.profile_posts_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), colNumb));
        // TODO: pass Posts list to adapter

        // mProfilePostAdapter = new ProfilePostAdapter(getContext(), new Post[]{});

        mProfilePostAdapter = new ProfilePostAdapter(getContext(), mData);
        mProfilePostAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mProfilePostAdapter);
        Log.i("RECYCLER ADAPTER", "GOT SET");
    }


    @Override
    public void onItemClick(View view, int position) {
        // TODO: pass event to View Model
        Toast.makeText(getContext(), "Clicked at: " + position,  Toast.LENGTH_SHORT).show();
    }


    private void setToolbar() {
        FragmentActivity fragmentActivity =  getActivity();
        if (fragmentActivity == null) return;
        ((AppCompatActivity) fragmentActivity).setSupportActionBar(mToolbar);
    }


    private class CustomPreloadModelProvider implements ListPreloader.PreloadModelProvider {

        @NonNull
        @Override
        public List getPreloadItems(int position) {
            String url = mData[position];
            if (url != null) {
                Log.i("GOT SINGLE URL", mData[position]);
                return Collections.singletonList(url);
            } else {
                return Collections.emptyList();
            }
        }


        @Nullable
        @Override
        public RequestBuilder<?> getPreloadRequestBuilder(@NonNull Object item) {
            Log.i("PRELOAD REQUEST BUILDER", item.toString().toUpperCase());
            return Glide.with(Objects.requireNonNull(getContext()))
                    .load(item.toString())
                    .placeholder(R.drawable.ic_search_black_24dp);
        }
    }
}


