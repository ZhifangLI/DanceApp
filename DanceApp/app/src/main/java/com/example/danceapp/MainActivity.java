package com.example.danceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button record;
    private Button stop;
    private Button estimate1,estimate2,estimate3,estimate4,estimate5;
    private Button newMovement;
    private String action;
    public static SensorManager sm;
    public static Sensor ac,gp;
    private static Database database = new Database();
    private Operation operation = new Operation();
    private int tableNumber = 0;
    private Context context;
    private double ac1,ac2,ac3,gp1,gp2,gp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        ac = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gp = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        context = MainActivity.this;
        database.myDBOpenHelper = new MyDBOpenHelper(context,"database.db",null,1);
        database.sqLiteDatabase = database.myDBOpenHelper.getWritableDatabase();
        operation.initializeClass();

    }


    public void changeToEasy(View view)
    {
        Intent intent = new Intent(MainActivity.this, EasyDanceScreen.class);
        startActivity(intent);
    }

    public void changeToNormal(View view)
    {
        Intent intent = new Intent(MainActivity.this, normalDanceScreen.class);
        startActivity(intent);
    }

    public void changeToHard(View view)
    {
        Intent intent = new Intent(MainActivity.this, hardDanceScreen.class);
        startActivity(intent);
    }

    public void changeToCustom(View view)
    {
        Intent intent = new Intent(MainActivity.this, customDanceScreen.class);
        startActivity(intent);
    }

    public void changeToLeaderboard(View view)
    {
        Intent intent = new Intent(MainActivity.this, leaderboard.class);
        startActivity(intent);
    }public static SensorManager getSm(){
        return sm;
    }
    public static Sensor getAc(){
        return ac;
    }
    public static Sensor getGp(){
        return gp;
    }
    public static Database getDatabase(){
        return database;
    }
}