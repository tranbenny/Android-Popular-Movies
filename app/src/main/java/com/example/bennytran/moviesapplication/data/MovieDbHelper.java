package com.example.bennytran.moviesapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by benny on 9/22/15.
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "popular_movies.db";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME +
                "(" + MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY, " +
                MovieContract.MovieEntry.COLUMN_TITLE + " TEXT UNIQUE NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_IMAGE_URL + "TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_OVERVIEW + "TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_RELEASE_DATE + "TEXT NOT NULL" +
                MovieContract.MovieEntry.COLUMN_SCORE + "DOUBLE NOT NULL ); ";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME); */
        onCreate(db);
    }
}
