package com.teamandroid.snapshare.ui.main.profile;


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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.data.model.Post;
import com.teamandroid.snapshare.databinding.FragmentProfileBinding;
import com.teamandroid.snapshare.utils.Constants;

import java.util.List;


public class ProfileFragment extends Fragment implements ProfilePostAdapter.ItemClickListener{
    private final int colNumb = R.dimen.profile_column_number;

    private FragmentProfileBinding mBinding;
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        View rootView = mBinding.getRoot();

        init(rootView);

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handleArguments();
        listenToPosts();
    }


    @Override
    public void onItemClick(View view, int position) {
        // TODO: add to back stack, open detail fragment
//        Toast.makeText(getContext(), "Clicked at: " + position,  Toast.LENGTH_SHORT).show();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, ProfileDetailedFragment.newInstance());
        fragmentTransaction.addToBackStack(this.getClass().getName());
        fragmentTransaction.commit();
    }



    private void init(View rootView) {
        mProfileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        mBinding.setProfileViewModel(mProfileViewModel);
        mBinding.setLifecycleOwner(this);

        mToolbar = rootView.findViewById(R.id.toolbar);
        setToolbar();

        mRecyclerView = rootView.findViewById(R.id.profile_posts_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), colNumb));
        mProfilePostAdapter = new ProfilePostAdapter(getContext());
        mProfilePostAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mProfilePostAdapter);
    }


    private void handleArguments() {
        //FIXME: get args from getArguments
        // Handle passed arguments to define running mode
//        Bundle args = getArguments();
        Bundle args = new Bundle();
        args.putString(Constants.PROFILE_USER_ID, "wed1EsMlVC62jmzvgyL8");
        args.putString(Constants.PROFILE_USER_DISPLAY_NAME, "Huy Le");
        args.putString(Constants.PROFILE_USER_GIVEN_NAME, "huylv");
        args.putString(Constants.PROFILE_USER_AVATAR, "https://storage.googleapis.com/firestorequickstarts.appspot.com/food_14.png");
        if (mProfileViewModel.displayingCurrentUser(args)) {
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
            mProfileViewModel.setProfileUser(account);
        } else {
            mProfileViewModel.setProfileUser(args);
        }
    }


    private void setToolbar() {
        FragmentActivity fragmentActivity =  getActivity();
        if (fragmentActivity == null) return;
        ((AppCompatActivity) fragmentActivity).setSupportActionBar(mToolbar);
    }


    private void listenToPosts() {
        mProfileViewModel.getUserPosts().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                mProfilePostAdapter.setData(posts);
            }
        });
    }
}

