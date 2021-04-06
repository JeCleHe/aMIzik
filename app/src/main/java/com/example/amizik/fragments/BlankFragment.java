 package com.example.amizik.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amizik.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

 public class BlankFragment extends Fragment {

    String title, url;

    TextView video_title;
    SimpleExoPlayer player;
   private PlayerView mExoplayerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public BlankFragment() {}

    public BlankFragment(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view = inflater.inflate(R.layout.fragment_blank, container, false);

             video_title = view.findViewById(R.id.video_title);
             mExoplayerView = view.findViewById(R.id.exoplayer_view);

             video_title.setText(title);

             try{
                 //ok
                 BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                 ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

                 TrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

                 //ok
                 TrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);

                 DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();



               DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                         Util.getUserAgent(getContext(), "video"),defaultBandwidthMeter);


                 MediaSource mediaSource = new ExtractorMediaSource(Uri.parse("https://www.youtube.com/watch?v=Lk2ys5svDyc&t=1625s"),
                         dataSourceFactory,
                         extractorsFactory,
                         null,
                         null);

                 player = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
                 mExoplayerView.setPlayer(player);
                 player.prepare(mediaSource);
                 player.setPlayWhenReady(true);
             } catch (Exception e){
                 Log.e("BlankFragment", "exoplayer error: " +  e.toString());
             }

             return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new VideoFragment())
                .addToBackStack(null).commit();
    }
}