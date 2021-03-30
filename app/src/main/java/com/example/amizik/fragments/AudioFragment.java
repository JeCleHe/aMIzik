package com.example.amizik.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.amizik.R;
import com.example.amizik.adapters.AudioAdapter;
import com.example.amizik.models.Audio;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class AudioFragment extends Fragment {


 RecyclerView reciview ;
 AudioAdapter adapter;
    public AudioFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        reciview = (RecyclerView) view.findViewById( R.id.reciview );
        reciview.setLayoutManager( new LinearLayoutManager( getContext() ) );

        FirebaseRecyclerOptions<Audio> options =
                new FirebaseRecyclerOptions.Builder<Audio>()
                        .setQuery( FirebaseDatabase.getInstance().getReference().child("audio"), Audio.class)
                        .build();
        adapter = new AudioAdapter( options );
        reciview.setAdapter( adapter );
        return view;
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