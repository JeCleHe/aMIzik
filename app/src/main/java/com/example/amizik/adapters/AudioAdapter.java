package com.example.amizik.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amizik.R;
import com.example.amizik.models.Audio;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class AudioAdapter extends FirebaseRecyclerAdapter<Audio, AudioAdapter.myViewholder> {



    public AudioAdapter ( @NonNull FirebaseRecyclerOptions<Audio> options ) {
        super( options );
    }

    @Override
    protected void onBindViewHolder ( @NonNull myViewholder holder, int position, @NonNull Audio model ) {
         holder.iv_tile.setText(  model.getTitle());

    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_view, parent, false );
        return new myViewholder( view );
    }

    public class myViewholder extends RecyclerView.ViewHolder{

    ImageView iv_image ;
    TextView iv_tile;

    public myViewholder ( @NonNull View itemView ) {
        super( itemView );

        iv_image = itemView.findViewById( R.id.iv_image );
        iv_tile  = itemView.findViewById( R.id.iv_title );
    }
}

}
