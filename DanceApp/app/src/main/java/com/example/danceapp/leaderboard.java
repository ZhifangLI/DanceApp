package com.example.danceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Intent intent = getIntent();
        String message = intent.getStringExtra(finish.EXTRA_MESSAGE);

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(message);
    }
}


