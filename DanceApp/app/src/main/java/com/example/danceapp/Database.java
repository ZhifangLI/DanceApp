package com.example.danceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Database {
    public SQLiteDatabase sqLiteDatabase;
    public MyDBOpenHelper myDBOpenHelper;
    public StringBuilder stringBuilder;
    public Context context;
    private int time=1;

    public void insertData(String table,ContentValues contentValues){
        sqLiteDatabase.insert(table,null,contentValues);
    }
    public void createTable(String table){
        sqLiteDatabase.execSQL("CREATE TABLE "+table+"(id INTEGER PRIMARY KEY AUTOINCREMENT,a double,b double,c double,d double,e double,f double)");
    }
    public boolean checkTable(String table){
        String instruct = "select count (*) from sqlite_master where type = 'table' and name = '"+table+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(instruct,null);
        if(cursor.moveToNext()){
            int count = cursor.getInt(0);
            if (count>0){
                cursor.close();
                return true;
            }
        }

        cursor.close();
        return false;
    }

    public int countColumn(String table){
        String instruct = "select count (*) from "+table;
        Cursor cursor = sqLiteDatabase.rawQuery(instruct,null);
        int count = 0;
        if(cursor.moveToNext()){
            count = cursor.getInt(0);
            if (count>0){
                cursor.close();
                return count;
            }
        }
        cursor.close();
        return count;
    }
    public double[] findValue (String table,Integer id){
        double[] a = new double[6];
        sqLiteDatabase = myDBOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+table+" WHERE id ="+id,
                null);
        if(cursor.moveToFirst()){
            a[0]=cursor.getDouble(cursor.getColumnIndex("a"));
            a[1]=cursor.getDouble(cursor.getColumnIndex("b"));
            a[2]=cursor.getDouble(cursor.getColumnIndex("c"));
            a[3]=cursor.getDouble(cursor.getColumnIndex("d"));
            a[4]=cursor.getDouble(cursor.getColumnIndex("e"));
            a[5]=cursor.getDouble(cursor.getColumnIndex("f"));
        }
        cursor.close();
        return a;
    }
}
