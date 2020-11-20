package com.example.moviesapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.moviesapp.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorite.db";

    private static final int DATABASE_VERSION = 1;

    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public FavoriteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        Log.i(LOGTAG, "Database Opened");
        db = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + FavoriteColuns.FavoriteEntry.TABLE_NAME + " (" +
                FavoriteColuns.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteColuns.FavoriteEntry.COLUMN_MOVIEID + " INTEGER, " +
                FavoriteColuns.FavoriteEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                FavoriteColuns.FavoriteEntry.COLUMN_RANK + " REAL NOT NULL, " +
                FavoriteColuns.FavoriteEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                FavoriteColuns.FavoriteEntry.COLUMN_OVERVIEW + " TEXT NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteColuns.FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addFavorite(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavoriteColuns.FavoriteEntry.COLUMN_MOVIEID, movie.getIdDetail());
        values.put(FavoriteColuns.FavoriteEntry.COLUMN_NAME, movie.getName());
        values.put(FavoriteColuns.FavoriteEntry.COLUMN_RANK, movie.getRank());
        values.put(FavoriteColuns.FavoriteEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(FavoriteColuns.FavoriteEntry.COLUMN_OVERVIEW, movie.getOverviewMovie());

        db.insert(FavoriteColuns.FavoriteEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean deleteFavorite(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FavoriteColuns.FavoriteEntry.TABLE_NAME, FavoriteColuns.FavoriteEntry.COLUMN_MOVIEID+ "=" + id, null) > 0;
    }



    public List<Movie> getAllFavorite(){
        String[] columns = {
                FavoriteColuns.FavoriteEntry._ID,
                FavoriteColuns.FavoriteEntry.COLUMN_MOVIEID,
                FavoriteColuns.FavoriteEntry.COLUMN_NAME,
                FavoriteColuns.FavoriteEntry.COLUMN_RANK,
                FavoriteColuns.FavoriteEntry.COLUMN_POSTER_PATH,
                FavoriteColuns.FavoriteEntry.COLUMN_OVERVIEW

        };
        String sortOrder = FavoriteColuns.FavoriteEntry._ID + " ASC";
        List<Movie> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteColuns.FavoriteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setIdDetail(cursor.getString(cursor.getColumnIndex(FavoriteColuns.FavoriteEntry.COLUMN_MOVIEID)));
                movie.setName(cursor.getString(cursor.getColumnIndex(FavoriteColuns.FavoriteEntry.COLUMN_NAME)));
                movie.setRank(cursor.getString(cursor.getColumnIndex(FavoriteColuns.FavoriteEntry.COLUMN_RANK)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(FavoriteColuns.FavoriteEntry.COLUMN_POSTER_PATH)));
                movie.setOverviewMovie(cursor.getString(cursor.getColumnIndex(FavoriteColuns.FavoriteEntry.COLUMN_OVERVIEW)));

                favoriteList.add(movie);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoriteList;
    }

}
