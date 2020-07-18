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

    public  void InsertDataSongLike(String table,String id,String name,String img,String singer,String link,String like ){
        SQLiteDatabase database=getWritableDatabase();
        String sql="Insert into '"+table+"' values(?,?,?,?,?,?)";

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

    public  void InsertDataPlaylistLike(String table,String id,String name,String img,String icon ){
        SQLiteDatabase database=getWritableDatabase();
        String sql="Insert into '"+table+"' values(?,?,?,?)";

        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,id);
        sqLiteStatement.bindString(2,name);
        sqLiteStatement.bindString(3,img);
        sqLiteStatement.bindString(4,icon);


        sqLiteStatement.executeInsert();

    }

    public  void InsertDataAlbumLike(String table,String id,String name,String singer,String img){
        SQLiteDatabase database=getWritableDatabase();
        String sql="Insert into '"+table+"' values(?,?,?,?)";

        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,id);
        sqLiteStatement.bindString(2,name);
        sqLiteStatement.bindString(3,singer);
        sqLiteStatement.bindString(4,img);
        sqLiteStatement.executeInsert();

    }

    public  void InsertDataCategoryLike(String table,String idCategory,String idTheme,String name,String img){
        SQLiteDatabase database=getWritableDatabase();
        String sql="Insert into '"+table+"' values(?,?,?,?)";

        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,idCategory);
        sqLiteStatement.bindString(2,idTheme);
        sqLiteStatement.bindString(3,name);
        sqLiteStatement.bindString(4,img);
        sqLiteStatement.executeInsert();

    }

    public  void InsertDataMVLike(String table,String id,String key,String imgMV,String imgSinger,String timeMV,String nameSong,String nameSinger,String likeMV){
        SQLiteDatabase database=getWritableDatabase();
        String sql="Insert into '"+table+"' values(?,?,?,?,?,?,?,?)";

        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,id);
        sqLiteStatement.bindString(2,key);
        sqLiteStatement.bindString(3,imgMV);
        sqLiteStatement.bindString(4,imgSinger);
        sqLiteStatement.bindString(5,timeMV);
        sqLiteStatement.bindString(6,nameSong);
        sqLiteStatement.bindString(7,nameSinger);
        sqLiteStatement.bindString(8,likeMV);

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
