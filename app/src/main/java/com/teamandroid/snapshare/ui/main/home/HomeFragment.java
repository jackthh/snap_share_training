package com.teamandroid.snapshare.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.data.model.Post;

import java.util.List;


public class HomeFragment extends Fragment {
    public static String TAG = HomeFragment.class.getSimpleName();
    private Toolbar mToolbar;
    private PostViewModel mPostViewModel;
    private PostListAdapter mAdapter;
    private RecyclerView postListRv;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        setToolbar();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postListRv = view.findViewById(R.id.rv_feed);
        postListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PostListAdapter();
        postListRv.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        getPosts();
    }

    private void setToolbar() {
        FragmentActivity fragmentActivity = getActivity();
        if (fragmentActivity == null) return;
        ((AppCompatActivity) fragmentActivity).setSupportActionBar(mToolbar);
    }

    private void initView(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
    }

    private void getPosts() {
        mPostViewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                mAdapter.setPostList(posts);
            }
        });
    }

}
