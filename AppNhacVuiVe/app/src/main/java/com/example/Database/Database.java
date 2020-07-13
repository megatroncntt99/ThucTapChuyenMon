package com.example.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  void SetData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);

    }
    public  void InsertDataSong(String id,String name,String img,String singer,String link,String like ){
        SQLiteDatabase database=getWritableDatabase();
        String sql="Insert into SongNew values(?,?,?,?,?,?)";

        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,id);
        sqLiteStatement.bindString(2,name);
        sqLiteStatement.bindString(3,img);
        sqLiteStatement.bindString(4,singer);
        sqLiteStatement.bindString(5,link);
        sqLiteStatement.bindString(6,like);

        sqLiteStatement.executeInsert();

    }


    public Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return  database.rawQuery(sql,null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
