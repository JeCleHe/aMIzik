package com.example.amizik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.FragmentVideoPlayer;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityVideoDetail extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_title) TextView tv_title;

    String videoId, title;
    FragmentVideoPlayer fragmentVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        ButterKnife.bind(this);

        getData();
        playVideo();
    }

    private void playVideo() {

        final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if(result != YouTubeInitializationResult.SUCCESS){
            result.getErrorDialog(this, 0).show();
            return;
        }

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.placeholder, fragmentVideoPlayer);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();

        fragmentVideoPlayer.setVideoId(videoId);
    }

    public void getData(){
        Intent intent = getIntent();

        videoId = intent.getStringExtra("videoId");
        title = intent.getStringExtra("videoTitle");

        tv_title.setText(title);
        setSupportActionBar(toolbar);
    }
}