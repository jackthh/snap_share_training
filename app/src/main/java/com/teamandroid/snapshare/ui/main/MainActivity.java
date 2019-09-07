package com.teamandroid.snapshare.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamandroid.snapshare.R;
import com.teamandroid.snapshare.ui.main.home.HomeFragment;
import com.teamandroid.snapshare.ui.main.search.SearchFragment;
import com.teamandroid.snapshare.ui.post.PostActivity;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = HomeFragment.newInstance();
                    mFragmentManager.beginTransaction()
                            .replace(R.id.content_container, homeFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_search:
                    //TODO
                    SearchFragment searchFragment = SearchFragment.newInstance();
                    mFragmentManager.beginTransaction()
                            .replace(R.id.content_container, searchFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;

                case R.id.navigation_add_post:
                    openPostActivity();
                    return true;
                case R.id.navigation_account:
                    //TODO
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        HomeFragment homeFragment = HomeFragment.newInstance();
        mFragmentManager.beginTransaction()
                .add(R.id.content_container, homeFragment)
                .addToBackStack(null)
                .commit();
    }

    private void openPostActivity() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }
}
