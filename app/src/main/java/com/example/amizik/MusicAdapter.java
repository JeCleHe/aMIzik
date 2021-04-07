package com.example.amizik;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Music> musicArrayList;
    private MediaPlayer mediaPlayer;
    private Boolean flag = true;

    public MusicAdapter(Context context, int layout, ArrayList<Music> musicArrayList) {
        this.context = context;
        this.layout = layout;
        this.musicArrayList = musicArrayList;
    }


    @Override
    public int getCount() {
        return musicArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return musicArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public  class viewHolder{
    TextView    textView_songName, textView_artist;
    ImageView   imageView_play, imageView_stop;
    private View convertview;
    LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    public viewHolder(){

        convertview = layoutInflater.inflate(layout,null);
        textView_songName = convertview.findViewById(R.id.textview_song);
        textView_artist = convertview.findViewById(R.id.textview_artist);
        imageView_play  = convertview.findViewById(R.id.imageView_play);
        imageView_stop  = convertview.findViewById(R.id.imageView_stop);

    }

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final viewHolder viewHolder = new viewHolder();
        if (view==null){
            view = viewHolder.convertview;
        }else {

        }

        final Music music = musicArrayList.get(i);

        viewHolder.textView_songName.setText(music.getSongName());

        viewHolder.textView_artist.setText(music.getArtiste());

        viewHolder.imageView_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    mediaPlayer =   MediaPlayer.create(context,music.getSongs());
                    flag    =   false;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    viewHolder.imageView_play.setImageResource(R.drawable.ic_baseline_play);
                }else {
                    mediaPlayer.start();
                    viewHolder.imageView_play.setImageResource(R.drawable.selector_pause);
                }
                mediaPlayer.start();
            }
        });

        viewHolder.imageView_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    flag    =   true;
                }
                    viewHolder.imageView_play.setImageResource(R.drawable.selector_stop);


            }
        });
        return view;
    }
}
