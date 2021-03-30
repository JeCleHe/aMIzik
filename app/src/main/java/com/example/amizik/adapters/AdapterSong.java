package com.example.amizik.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amizik.models.ReadSong;

import java.util.List;

public class AdapterSong  extends RecyclerView.Adapter<AdapterSong.Song> {

 Context context;
 List<ReadSong> arraylist;
 private RecyclerViewItem item;

    public AdapterSong ( Context context, List<ReadSong> arraylist, RecyclerViewItem item ) {
        this.context = context;
        this.arraylist = arraylist;
        this.item = item;
    }

    @NonNull
    @Override
    public Song onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        return null;
    }

    @Override
    public void onBindViewHolder ( @NonNull Song holder, int position ) {

    }

    @Override
    public int getItemCount () {
        return 0;
    }

    public class Song extends RecyclerView.ViewHolder {

        public Song ( @NonNull View itemView ) {
            super( itemView );
        }
    }

    private interface RecyclerViewItem {
    }
}
