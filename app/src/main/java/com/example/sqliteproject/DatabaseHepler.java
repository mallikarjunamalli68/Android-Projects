package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHepler extends SQLiteOpenHelper {

    public static final String databseName = "signUp.db";
    public DatabaseHepler(@Nullable Context context) {
        super(context, "signUp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabese) {
        MyDatabese.execSQL("create Table allUsers(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists allUsers");
    }

    public boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = MyDatabase.insert("allUsers", null, contentValues);

        return result != -1;
    }

    public boolean checkEmail(String email){
        SQLiteDatabase MyDatabse = this.getWritableDatabase();
        Cursor cursor = MyDatabse.rawQuery("Select * from allUsers where email = ?", new String[]{email});

        return cursor.getCount() > 0;
    }

    public boolean checkEmailAndPassword(String email, String password){
        SQLiteDatabase MyDatabse = this.getWritableDatabase();
        Cursor cursor = MyDatabse.rawQuery("Select * from allUsers where email = ? and password = ?", new String[]{email, password});

        return cursor.getCount() > 0;
    }
}
