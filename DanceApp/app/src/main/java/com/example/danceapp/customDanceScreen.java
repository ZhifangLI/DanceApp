package com.example.danceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import java.util.Random;

public class customDanceScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dance_screen);

        ImageView[] ivselection = new ImageView[5];

        final ImageView ImageView1 = findViewById(R.id.imageView1);
        ImageView1.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView2 = findViewById(R.id.imageView2);
        ImageView2.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView3 = findViewById(R.id.imageView3);
        ImageView3.setImageResource(R.drawable.ic_launcher_background);

        Button button = (Button) findViewById(R.id.button7);


    }

}