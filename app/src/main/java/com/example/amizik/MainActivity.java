package com.example.amizik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private static  int timeout = 3000;
TextView amizik , ayizyen;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amizik = (TextView) findViewById(R.id.amizik);
        ayizyen= (TextView) findViewById(R.id.ayizyen);
        image = (ImageView) findViewById(R.id.image);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this ,R.anim.myanimation);
        image.startAnimation(animation);
        amizik.startAnimation(animation);
        ayizyen.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
  Intent intent = new Intent( MainActivity.this , LoginActivity.class);
  startActivity(intent);
  finish();

          }
        } ,timeout);
    }
}
