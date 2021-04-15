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
import android.widget.Toast;

import com.example.amizik.R;
import com.example.amizik.adapters.VideoAdapter;
import com.example.amizik.models.ResponseVideos;
import com.example.amizik.models.Video;
import com.example.amizik.retrofit.ApiClient;
import com.example.amizik.retrofit.ApiInterface;
import com.example.amizik.utils.MyConsts;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoFragment extends Fragment {

    @BindView(R.id.rvVideo) RecyclerView rvVideo;
    @BindView(R.id.progressBarVideos) ProgressBar progressBar;
    VideoAdapter videoAdapter;
    ArrayList<Video> arrayListVideos;
    ApiInterface apiInterface;

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
        arrayListVideos = new ArrayList<>();

        videoAdapter = new VideoAdapter(getContext(), arrayListVideos);
        rvVideo.setAdapter(videoAdapter);
        getVideo();
    }

    private void getVideo(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ResponseVideos> callVideo = apiInterface.getAllvideos(20, MyConsts.PLAYLIST_ID, MyConsts.APIKEY);

        callVideo.enqueue(new Callback<ResponseVideos>() {
            @Override
            public void onResponse(Call<ResponseVideos> call, Response<ResponseVideos> response) {
                ResponseVideos responseVideos = response.body();
                if(responseVideos != null){
                    progressBar.setVisibility(View.GONE);
                    if(responseVideos.items.size() > 0){
                        for(int i = 0; i < responseVideos.items.size(); i++){
                            arrayListVideos.add(responseVideos.items.get(i));
                        }

                        videoAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getContext(), "Il n'y a pas de videos.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseVideos> call, Throwable t) {

            }
        });
    }

}
