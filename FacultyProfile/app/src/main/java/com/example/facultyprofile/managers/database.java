package com.example.facultyprofile.managers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class database extends SQLiteOpenHelper {

    //Constants for Database name, table name, and column names
    public static final String DB_NAME = "db";
    public static final String TABLE_NAME = "student";
    public static final String COlUMN_NAME = "name";
    SQLiteDatabase db;


    //database version
    private static final int DB_VERSION = 1;

    //Constructor
    public database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    //creating the database
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR);");
        Log.d("myApp", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /*
     * This method is taking two arguments
     * first one is the name that is to be saved
     * second one is the status
     * 0 means the name is synced with the server
     * 1 means the name is not synced with the server
     * */
    public boolean addName(String name1) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT INTO student VALUES('" + name1 + "');");
        Log.d("myApp", "Inserted");
        db.close();
        return true;
    }
    public boolean delete(String name1) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+COlUMN_NAME+"='"+name1+"'");
        Log.d("myApp", "deleted");
        db.close();
        return true;
    }
    public boolean check(String name1) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT EXISTS (SELECT * FROM student WHERE name='"+name1+"' LIMIT 1)";
        Cursor cursor = db.rawQuery(Query, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            Log.d("Hello",name1);
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM student ", null);
        if (c.moveToFirst()) {
            do {
                // Passing values
                String column1 = c.getString(0);
                Log.d("myApp", column1+"saasdasd");
                // Do something Here with values
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return true;
    }

    public Cursor list() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM student ", null);

        return c;
    }







}