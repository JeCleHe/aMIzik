package com.google.android.youtube.player;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amizik.R;
import com.example.amizik.utils.MyConsts;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;


public class FragmentVideoPlayer extends YouTubePlayerSupportFragmentX implements YouTubePlayer.OnInitializedListener {

  public interface OnVideoPlayListener{
      void onPlaying(String videoId);
  }

  String videoId;

  OnVideoPlayListener onVideoPlayListener;

    // Required empty public constructor
    public FragmentVideoPlayer() {}

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        final Bundle arguments = getArguments();

        if(bundle != null && bundle.containsKey("KEY_VIDEO_ID")){
            videoId = bundle.getString("KEY_VIDEO_ID");
        } else {
            videoId = arguments.getString("KEY_VIDEO_ID");
        }

        initialize(MyConsts.APIKEY, this);
    }

    public void setVideoId(final String mVideoId) {
        videoId = mVideoId;
        initialize(MyConsts.APIKEY, this);
    }

/* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
       View view = inflater.inflate(R.layout.fragment_video_player, container, false);
       return view;
    }*/

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(videoId != null){
            if(b){
                youTubePlayer.play();
            } else {
                youTubePlayer.loadVideo(videoId);
            }

            if(onVideoPlayListener != null){
                onVideoPlayListener.onPlaying(videoId);
            }
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(getActivity(), 1).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("KEY_VIDEO_ID", videoId);
    }
}