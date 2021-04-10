package com.example.amizik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.youtube.player.FragmentVideoPlayer;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityVideoDetail extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_title) TextView tv_title;

    String videoId, title;

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

        FragmentVideoPlayer fragmentVideoPlayer = (FragmentVideoPlayer)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_youtube);

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