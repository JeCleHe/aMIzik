package com.example.amizik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        //to change the toolbar's color
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);

        if(OptionActivity.videoIsClicked == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new VideoFragment()).commit();
        }else if(OptionActivity.audioIsclicked == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AudioFragment()).commit();
        }
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