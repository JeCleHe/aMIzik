package com.example.amizik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.amizik.fragments.AudioFragment;
import com.example.amizik.fragments.ProfileFragment;
import com.example.amizik.fragments.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);

        //To display the video fragment by default
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
               new VideoFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_video:
                            selectedFragment = new VideoFragment();
                            break;

                        case R.id.nav_audio:
                            selectedFragment = new AudioFragment();
                            break;

                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}