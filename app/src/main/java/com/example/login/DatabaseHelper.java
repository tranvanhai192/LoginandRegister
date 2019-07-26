package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user(username text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
    }
        // thêm user + pass vào table
        public boolean addUser(String username, String password) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("password", password);

            long res = db.insert("user", null, contentValues);
            if (res == -1) return false;
            else return true;
        }


        public boolean chname(String username){
            SQLiteDatabase db=this.getWritableDatabase();
            Cursor cursor=db.rawQuery("Select * from user where username=?",new String[]{username});
            if(cursor.getCount()>0) return false;
            else return true;
        }


        // kiểm tra user + pass
        public boolean checkuser(String username, String password) {

            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery("select * from user where username=? and password=?",new String[]{username,password});
            if(cursor.getCount()>0) return true;
            else{
                return false;
            }

        }

    }

