package com.example.amizik.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amizik.NavigationActivity;
import com.example.amizik.PlayVideoActivity;
import com.example.amizik.R;
import com.example.amizik.fragments.BlankFragment;
import com.example.amizik.models.Video;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VideoAdapter extends FirebaseRecyclerAdapter<Video, VideoAdapter.ViewHolder> {

    public VideoAdapter(@NonNull FirebaseRecyclerOptions<Video> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Video video) {
       holder.bind(video);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new BlankFragment(video.getTitle(), video.getUrl()))
                        .addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView videoImg;
        TextView videoTitle;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoImg = itemView.findViewById(R.id.videoImg);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Video video) {
            videoTitle.setText(video.getUrl());
            Glide.with(videoImg.getContext())
                    .load(video.getPoster())
                    .centerCrop()
                    .placeholder(R.drawable.video_icon)
                    .into(videoImg);
        }

    }
}
