package com.example.amizik.models;

public class Audio {

    String title ;
    String url;

    public Audio ( String title, String url ) {
        this.title = title;
        this.url = url;
    }

    public Audio () {
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
}
