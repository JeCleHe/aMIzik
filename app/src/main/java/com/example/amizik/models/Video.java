package com.example.amizik.models;

import org.parceler.Parcel;

@Parcel
public class Video {

    private String title;
    private String url;
    private String poster;

    public Video(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
