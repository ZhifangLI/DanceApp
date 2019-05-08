package com.example.danceapp;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Vibrator;
import android.view.View.OnClickListener;

import java.util.Random;

public class hardDanceScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_dance_screen);

        ImageView[] ivselection = new ImageView[5];

        final ImageView ImageView21 = findViewById(R.id.imageView21);
        ImageView21.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView22 = findViewById(R.id.imageView22);
        ImageView22.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView23 = findViewById(R.id.imageView23);
        ImageView23.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView24 = findViewById(R.id.imageView24);
        ImageView24.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView25 = findViewById(R.id.imageView25);
        ImageView25.setImageResource(R.drawable.ic_launcher_background);

        Button button = (Button) findViewById(R.id.button9);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch(randomMove()){
                    case 0:
                        ImageView21.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView21.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView21.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView21.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView21.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView22.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView22.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView22.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView22.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView22.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView23.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView23.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView23.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView23.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView23.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView24.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView24.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView24.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView24.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView24.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView25.setImageResource(R.drawable.img_2);
                        break;
                    case 1:
                        ImageView25.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView25.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView25.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView25.setImageResource(R.drawable.img_4);
                        break;
                }
            }
        });

        final Button playButton = findViewById(R.id.buttonPlayHard);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Viber("on", getApplicationContext());

                new CountDownTimer(10500, 1500) {

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
        Intent intent = new Intent(hardDanceScreen.this, finish.class);
        int Score = 5;
        intent.putExtra("Score", Score);
        startActivity(intent);
    }

}
