package com.teamandroid.snapshare.ui.main.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.data.model.Post;


public class ProfileFragment extends Fragment implements ProfilePostAdapter.ItemClickListener{
    private final int colNumb = 3;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProfilePostAdapter mProfilePostAdapter;
    private ProfileViewModel mProfileViewModel;

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
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.profile_posts_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), colNumb));
        // TO DO: pass Posts list to adapter
        mProfilePostAdapter = new ProfilePostAdapter(getContext(), new Post[]{});
        mProfilePostAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mProfilePostAdapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        // TO DO: pass event to View Model
    }


    private void setToolbar() {
        FragmentActivity fragmentActivity =  getActivity();
        if (fragmentActivity == null) return;
        ((AppCompatActivity) fragmentActivity).setSupportActionBar(mToolbar);
    }

}
