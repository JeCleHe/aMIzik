package com.example.amizik.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.amizik.Music;
import com.example.amizik.MusicAdapter;
import com.example.amizik.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioFragment extends Fragment {

    @BindView(R.id.listeViewSong) RecyclerView listeViewSong;
    private ArrayList<Music> my_arraylist;
    private MusicAdapter m_musicAdapter;
    private ListView listView_song;

    public AudioFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }
}