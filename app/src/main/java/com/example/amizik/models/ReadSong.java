package com.example.amizik.models;

public class ReadSong {
    String title , url ,duration;
    String mkey;
    public ReadSong ( String title, String url, String duration ) {

        if (title.trim().equals( " " )){
            title = "Pas de musique "
        }

        this.title = title;
        this.url = url;
        this.duration = duration;
    }

    public ReadSong () {

    }

    public String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl ( String url ) {
        this.url = url;
    }

    public void setDuration ( String duration ) {
        this.duration = duration;
    }

    public void setMkey ( String mkey ) {
        this.mkey = mkey;
    }

    public String getDuration () {
        return duration;
    }

    public String getMkey () {
        return mkey;
    }
}
