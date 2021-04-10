package com.example.amizik.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amizik.ActivityVideoDetail;
import com.example.amizik.R;

import com.example.amizik.models.TypeThumbnail;
import com.example.amizik.models.Video;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    Context context;
    ArrayList<Video> arrayListVideos;

    public VideoAdapter(Context context, ArrayList<Video> arrayListVideos) {
        this.context = context;
        this.arrayListVideos = arrayListVideos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = arrayListVideos.get(position);
        if(video != null){
            holder.videoTitle.setText(video.snippet.title);
            if(video.snippet.thumbnails != null){
                TypeThumbnail t = video.snippet.thumbnails.high;
                if(t == null){
                    t = video.snippet.thumbnails.medium;
                }
                if(t != null){
                    t = video.snippet.thumbnails.standard;
                    Glide.with(context).load(t.url).into(holder.videoImg);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayListVideos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.videoImg) ImageView videoImg;
        @BindView(R.id.videoTitle) TextView videoTitle;
        @BindView(R.id.container) RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Video videoObj = arrayListVideos.get(getAdapterPosition());
                    Intent intent = new Intent(context, ActivityVideoDetail.class);
                    intent.putExtra("videoId", videoObj.contentDetails.videoId);
                    intent.putExtra("videoTitle", videoObj.snippet.title);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

}