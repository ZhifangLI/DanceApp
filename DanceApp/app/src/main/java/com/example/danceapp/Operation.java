package com.example.danceapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.lang.Math;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.content.ContentValues;

public class Operation {
    public  SensorManager sm;
    public  Sensor ac;
    public  Sensor gp;
    public  double ac1,ac2,ac3,gp1,gp2,gp3;
    private Database database;
    private int format=100;
    private boolean timer=false;
    private int tableNumber = 0;
    public void recordMovement(){
        timer = true;
        while(database.checkTable("temp"+tableNumber)){
            ++tableNumber;
        }
        database.createTable("temp"+tableNumber);
//        while(timer<1000000000){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("a",ac1);
//            contentValues.put("b",ac2);
//            contentValues.put("c",ac3);
//            contentValues.put("d",gp1);
//            contentValues.put("e",gp2);
//            contentValues.put("f",gp3);
//            database.insertData("temp"+tableNumber,contentValues);
//            try {
//                Thread.sleep(1000);
//                ++timer;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            handler.postDelayed(runnable,500);
//        }
    }
    public void stopRecording(){
        if(!timer){
            return;
        }
        timer = false;
        tableNumber=0;
        while(database.checkTable("final"+tableNumber)){
            ++tableNumber;
        }
        database.createTable("final"+tableNumber);
        int count = database.countColumn("temp"+tableNumber);
        if(count<format){
            for(int i=1;i<count;++i){
                double[] value;
                value = database.findValue("temp"+tableNumber,i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("a",value[0]);
                contentValues.put("b",value[1]);
                contentValues.put("c",value[2]);
                contentValues.put("d",value[3]);
                contentValues.put("e",value[4]);
                contentValues.put("f",value[5]);
                database.insertData("final"+tableNumber,contentValues);
            }
        }else{
            for(int i=1;i<format;++i){
                double[] value;
                int id = i*count/format;
                value = database.findValue("temp"+tableNumber,id);
                ContentValues contentValues = new ContentValues();
                contentValues.put("a",value[0]);
                contentValues.put("b",value[1]);
                contentValues.put("c",value[2]);
                contentValues.put("d",value[3]);
                contentValues.put("e",value[4]);
                contentValues.put("f",value[5]);
                database.insertData("final"+tableNumber,contentValues);
            }
        }
    }
    public void cancelRecording(){
        timer = false;
    }

    public double estimateMovement(String movement){
        while(database.checkTable("final"+tableNumber)){
            ++tableNumber;
        }
        --tableNumber;
        Estimation.setPercentage(0);
        int count = database.countColumn("final"+tableNumber);
        int count2 = database.countColumn(movement+"A");
        int a;
        if(count<=count2){a=count;}
        else{a=count2;}
        for(int i=1;i<a;++i){
                    double[] mean = database.findValue(movement+"A",i);
                    double[] stdev = database.findValue(movement+"S",i);
                    double[] value = database.findValue("final"+tableNumber,i);
                    Estimation.Estimator(mean[0],stdev[0],value[0]);
                    Estimation.Estimator(mean[1],stdev[1],value[1]);
                    Estimation.Estimator(mean[2],stdev[2],value[2]);
                    Estimation.Estimator(mean[3],stdev[3],value[3]);
                    Estimation.Estimator(mean[4],stdev[4],value[4]);
                    Estimation.Estimator(mean[5],stdev[5],value[5]);
                }
        double result = Estimation.getPercentage()/6/a;
        result = result*2;
        while(result>=1){
            result = result*0.9;
        }
        tableNumber=0;
        return result;
    }
    public void newMovenment(){
        int movement = 0;
        while(database.checkTable("movement"+movement+"A")){
                ++movement;
        }
        database.createTable("movement"+movement+"A");
        double ave[];
        boolean check=true;
        int id = 1;
        int count = 0;
        while(database.checkTable("final"+tableNumber)){
            ++tableNumber;
        }
        while(check){
            ave = new double[6];
            for(int i=0;i<=tableNumber;++i){
                if(database.checkTable("final"+i)){
                    if(database.countColumn("final"+i)<id){
                        check = false;
                        break;
                    }
                    double data[]=database.findValue("final"+i,id);
                    for(int j=0;j<6;++j){
                        ave[j]+=data[j];
                    }
                    ++count;
                }
            }
            for(int j=0;j<6;++j){
                ave[j]= ave[j]/count;
            }
            count=0;
            ContentValues contentValues = new ContentValues();
            contentValues.put("a",ave[0]);
            contentValues.put("b",ave[1]);
            contentValues.put("c",ave[2]);
            contentValues.put("d",ave[3]);
            contentValues.put("e",ave[4]);
            contentValues.put("f",ave[5]);
            database.insertData("movement"+movement+"A",contentValues);
            ++id;
        }
        database.createTable("movement"+movement+"S");
        double std[];
        check=true;
        id = 1;
        count = 0;
        while(check){
            std = new double[6];
            for(int i=0;i<=tableNumber;++i){
                if(database.checkTable("final"+i)){
                    if(database.countColumn("final"+i)<id){
                        check = false;
                        break;
                    }
                    double data[]=database.findValue("final"+i,id);
                    ave=database.findValue("movement"+movement+"A",id);
                    for(int j=0;j<6;++j){
                        std[j]+=(data[j]-ave[j])*(data[j]-ave[j]);
                    }
                    ++count;
                }
            }
            for(int j=0;j<6;++j){
                std[j] = Math.sqrt(std[j]/(count-1));
            }
            count=0;
            ContentValues contentValues = new ContentValues();
            contentValues.put("a",std[0]);
            contentValues.put("b",std[1]);
            contentValues.put("c",std[2]);
            contentValues.put("d",std[3]);
            contentValues.put("e",std[4]);
            contentValues.put("f",std[5]);
            database.insertData("movement"+movement+"S",contentValues);
            ++id;
        }
        tableNumber=0;
//        for(int i=0;i<tableNumber;++i){
//            if(database.checkTable("final"+i)){
//                double data[]=database.findValue("final"+i,);
//            }
//        }
    }
    public void initializeClass(){
        sm = MainActivity.getSm();//sensormanager
        ac = MainActivity.getAc();//accelerometer
        gp = MainActivity.getGp();//gyroscope
        database = MainActivity.getDatabase();
        SensorEventListener acSensorListener = new SensorEventListener(){
            public void onSensorChanged(SensorEvent event) {
                ac1 = event.values[0];
                ac2 = event.values[1];
                ac3 = event.values[2];
                if(timer) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("a",ac1);
                    contentValues.put("b",ac2);
                    contentValues.put("c",ac3);
                    contentValues.put("d",gp1);
                    contentValues.put("e",gp2);
                    contentValues.put("f",gp3);
                    database.insertData("temp"+tableNumber,contentValues);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        /**
         *  Create gyroscope sensor listener
         */
        SensorEventListener gpSensorListener = new SensorEventListener(){
            public void onSensorChanged(SensorEvent event) {
                gp1 = event.values[0];
                gp2 = event.values[1];
                gp3 = event.values[2];
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sm.registerListener(acSensorListener,ac,SensorManager.SENSOR_DELAY_GAME);
        sm.registerListener(gpSensorListener,gp,SensorManager.SENSOR_DELAY_GAME);
    }
//    Handler handler = new Handler();
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            handler.postDelayed(this,500);
//            ++timer;
//        }
//    };

}
