package com.example.danceapp;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import java.util.Random;

public class normalDanceScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_dance_screen);

        ImageView[] ivselection = new ImageView[5];

        final ImageView ImageView11 = findViewById(R.id.imageView11);
        ImageView11.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView12 = findViewById(R.id.imageView12);
        ImageView12.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView13 = findViewById(R.id.imageView13);
        ImageView13.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView14 = findViewById(R.id.imageView14);
        ImageView14.setImageResource(R.drawable.ic_launcher_background);

        Button button = (Button) findViewById(R.id.button8);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch(randomMove()){
                    case 0:
                        ImageView11.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView11.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView11.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView11.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView11.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView12.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView12.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView12.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView12.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView12.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView13.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView13.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView13.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView13.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView13.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView14.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView14.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView14.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView14.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView14.setImageResource(R.drawable.img_4);
                        break;
                }
            }
        });


        final Button playButton = findViewById(R.id.buttonPlayNormal);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Viber("on", getApplicationContext());

                new CountDownTimer(9000, 1500) {

                    public void onTick(long millisUntilFinished) {
                        Viber("on", getApplicationContext());}

                    public void onFinish() {

                    }
                }.start();
            }


        });
    }

    public void Viber(String value, Context context) {
        if (value.equals("on")) {
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(300);
        }
    }
    protected int randomMove(){
        Random rand = new Random();
        int randomInt = (int) rand.nextInt(4);

        return randomInt;

    }
    public void changeToFinish(View view)
    {
        Intent intent = new Intent(normalDanceScreen.this, finish.class);
        int Score = 4;
        intent.putExtra("Score", Score);
        startActivity(intent);
    }

}

