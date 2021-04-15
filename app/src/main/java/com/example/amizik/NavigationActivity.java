package com.example.amizik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.amizik.adapters.AdapterViewPager;
import com.example.amizik.fragments.AudioFragment;
import com.example.amizik.fragments.ProfileFragment;
import com.example.amizik.fragments.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.bottom_navigation) BottomNavigationView bottom_navigation;

    private final List<Fragment> fragmentList = new ArrayList<>();

    AdapterViewPager adapterViewPager;

    VideoFragment videoFragment;
    AudioFragment audioFragment;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        //to change the toolbar's color
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        videoFragment = new VideoFragment();
        audioFragment = new AudioFragment();
        profileFragment = new ProfileFragment();
        fragmentList.clear();

        fragmentList.add(videoFragment);
        fragmentList.add(audioFragment);
        fragmentList.add(profileFragment);

        viewPager.setOffscreenPageLimit(3);

        adapterViewPager = new AdapterViewPager(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                bottom_navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        bottom_navigation.setOnNavigationItemSelectedListener(navigationListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    
                    switch(item.getItemId()){
                        case R.id.nav_video:
                           viewPager.setCurrentItem(0);
                            break;

                        case R.id.nav_audio:
                            viewPager.setCurrentItem(1);
                            break;

                        case R.id.nav_profile:
                            viewPager.setCurrentItem(3);
                            break;
                    }
                    return true;
                }
            };
}