package com.example.amizik.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.amizik.R;
import com.example.amizik.adapters.VideoAdapter;
import com.example.amizik.models.Video;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment {

    @BindView(R.id.rvVideo) RecyclerView rvVideo;
    @BindView(R.id.progressBarVideos) ProgressBar progressBar;
    VideoAdapter videoAdapter;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_video, container, false);

       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        rvVideo.setHasFixedSize(true);
        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));


        videoAdapter = new VideoAdapter();
        rvVideo.setAdapter(videoAdapter);

    }

}
