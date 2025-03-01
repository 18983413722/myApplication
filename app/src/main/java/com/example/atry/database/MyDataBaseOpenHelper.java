package com.example.atry.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.atry.bean.Notepad;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseOpenHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "notepad.db";

    public MyDataBaseOpenHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "create table notepad(id integer primary key autoincrement,content text,time text)";
       db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion, int newVersion) {

    }
    public void insert(Notepad notepad  ){
        String sql = "insert into notepad(content,time) values(?,?)";
        SQLiteDatabase SQliteDatabase = getWritableDatabase();
        SQliteDatabase.execSQL(sql,new String[]{notepad.getContent(),notepad.getTime()});
    }
    //展示列表
    public List<Notepad> show(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "select * from notepad";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            List<Notepad>list = new ArrayList<>();
            while (cursor.moveToNext()){
                Notepad notepad = new Notepad();
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                notepad.setId(id);
                notepad.setContent(content);
                notepad.setTime(time);
                list.add(notepad);
            }
            cursor.close();
            return list;
    }
    //删除数据帮助
    public void delete(Notepad notepad) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "delete from notepad where id =?";
        sqLiteDatabase.execSQL(sql,new Object[]{notepad.getId()});
    }
}
