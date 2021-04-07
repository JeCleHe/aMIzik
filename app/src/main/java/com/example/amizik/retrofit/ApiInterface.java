package com.example.amizik.retrofit;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("playlistItems?part=snippet%2CcontentDetails")
    Call<Response> getAllvideos(@Query("maxResults") int max,
                                @Query("playlistId") String playlistId,
                                @Query("key") String apiKey);
}
