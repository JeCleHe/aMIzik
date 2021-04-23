package com.example.amizik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amizik.fragments.AudioFragment;
import com.example.amizik.fragments.VideoFragment;

public class OptionActivity extends AppCompatActivity {

    private TextView tvAudio, tvVideo;
    private ImageView ivAudio, ivVideo;
    public static boolean videoIsClicked, audioIsclicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        //to change the toolbar's color
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

       // tvVideo = findViewById(R.id.tvVideo);
        tvAudio = findViewById(R.id.tvAudio);
        //ivVideo = findViewById(R.id.ivVideo);
        ivAudio = findViewById(R.id.ivAudio);

    /*    ivVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoIsClicked = true;
                Intent i = new Intent(OptionActivity.this, VideosActivity.class);
                startActivity(i);
            }
        });*/

        /*tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoIsClicked = true;
                Intent i = new Intent(OptionActivity.this, VideosActivity.class);
                startActivity(i);
            }
        });
*/
        ivAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioIsclicked = true;
                Intent i = new Intent(OptionActivity.this, SongsActivity.class);
                startActivity(i);
            }
        });

        tvAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioIsclicked = true;
                Intent i = new Intent(OptionActivity.this, SongsActivity.class);
                startActivity(i);
            }
        });
    }
}