package com.example.danceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import 	android.os.CountDownTimer;
import android.view.View;

public class Error extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

    new CountDownTimer(2000, 500) {

        public void onTick(long millisUntilFinished) {
            Viber("on", getApplicationContext());}

        public void onFinish() {

        }
    }.start();
}

    public void changeToMenu(View view)
    {
        Intent intent = new Intent(Error.this, MainActivity.class);
        startActivity(intent);
    }

    public void Viber(String value, Context context) {
        if (value.equals("on")) {
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(300);
        }
    }
}
