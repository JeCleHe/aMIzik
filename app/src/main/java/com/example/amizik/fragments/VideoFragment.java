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

import com.example.amizik.R;
import com.example.amizik.adapters.VideoAdapter;
import com.example.amizik.models.Video;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VideoFragment extends Fragment {

    RecyclerView rvVideo;
    VideoAdapter adapter;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvVideo = (RecyclerView) view.findViewById(R.id.rvVideo);
        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Video> options =
                new FirebaseRecyclerOptions.Builder<Video>()
                        .setQuery( FirebaseDatabase.getInstance().getReference().child("video"), Video.class)
                        .build();

        adapter = new VideoAdapter(options);
        rvVideo.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
