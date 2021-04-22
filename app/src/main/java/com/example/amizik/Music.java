package com.example.amizik;

public class Music {
    private String songName;
    private String  artiste;
    private int  songs;

    public Music(String songName, String artiste, int songs) {
        this.songName = songName;
        this.artiste = artiste;
        this.songs = songs;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public int getSongs() {
        return songs;
    }

    public void setSongs(int songs) {
        this.songs = songs;
    }
}
