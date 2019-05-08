package com.example.danceapp;
import java.lang.Math;
import org.apache.commons.math3.distribution.NormalDistribution;
//import org.apache.commons.math3.distribution.


public class Estimation {
    public static double percentage=0;
    public static double result = 0;
    public static double a=0;
    public static NormalDistribution normalDistribution;
    public static void Estimator(double mean,double stdev,double value){
        double a= 0.000001;
        if(stdev==0){stdev=a;}
        normalDistribution = new NormalDistribution(mean,stdev);
        double probility = normalDistribution.cumulativeProbability(value);
        if(probility>0.5){
            probility = 2*(1-probility);
        }else {
            probility = probility*2;
        }
//        probility = (1-probility)*0.9+probility;
        percentage = percentage+probility;
    }
    public static double getPercentage(){
        return percentage;
    }
    public static void setPercentage(double a){
        percentage = a;
    }
    public static double getResult(){
        return  result;
    }
    public static double getA(){
        return a;
    }
}
