package com.example.danceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class finish extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.danceapp.MESSAGE";
    public int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Bundle extras = getIntent().getExtras();
        Score = extras.getInt("Score");
        final TextView displayScore = findViewById(R.id.displayScore);
        displayScore.setText("Score = " + Score);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, leaderboard.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString() + "    " + Score;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
