package com.example.amizik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AudioPlayer extends AppCompatActivity {


//    String[] iterms;

    private ArrayList<Music> my_arraylist;
    private MusicAdapter    m_musicAdapter;
    private  ListView listView_song;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        listView_song = findViewById(R.id.listeViewSong);
        my_arraylist    =   new ArrayList();

        my_arraylist.add(new Music("Wiliadel Denervil Feat Salomon Lira Salil - Map Viv Pou Ou", "Wiliadel", R.raw.luc));
        my_arraylist.add(new Music("Ranpli-m__Paster_Widmarc(128k)", "Widmarc", R.raw.widmarc));
        my_arraylist.add(new Music("Bondye konnleve andinite 03(15336)-1", "Anonym", R.raw.anonym));
        my_arraylist.add(new Music("ELKANA(128k)", "Anonym", R.raw.kana));
        my_arraylist.add(new Music("Ta parole", "Anonym", R.raw.parole));
        my_arraylist.add(new Music("Transfome lavi m(1)", "Anonyme", R.raw.former));
        my_arraylist.add(new Music("Gade'm la toujou(Ti bob)", "Tibob", R.raw.tibob));
        my_arraylist.add(new Music("Gen_Konfiance_Original_By_Rose_Georges(128k)", "Rose", R.raw.rose));
        my_arraylist.add(new Music("JEZI sifi pou mwen", "Anonyme", R.raw.jezi));
        my_arraylist.add(new Music("Kris nan bak mwen - Salomon Lira - (Lyric Koze Kretyen)", "Salomon", R.raw.salomon));
        my_arraylist.add(new Music("Lè Jezi Di Wi Vèsyn anglè", "DEG", R.raw.deg));
        my_arraylist.add(new Music("M_Paka_Plenyen_Pou_Tout_Sa_Jezu_Fe_Pou_Mwen_(pasteur_Reno)(128k)", "Anonyme", R.raw.nonyme));
        my_arraylist.add(new Music("Sasa fe si wout la move", "Anonyme", R.raw.route));
        my_arraylist.add(new Music("A_LA_YON_TAN_SEIGNEUR(128k)", "Anonyme", R.raw.yontan));
        my_arraylist.add(new Music("Benedict_Lamartine_-_Jesu_Kapab(128k)", "Benedict", R.raw.benedic));
        MusicAdapter adapter    =   new MusicAdapter(this, R.layout.song_items_listview,my_arraylist);
        listView_song.setAdapter(adapter);

    }

}