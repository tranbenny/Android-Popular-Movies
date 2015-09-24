package com.example.bennytran.moviesapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.bennytran.moviesapplication.data.MovieContract;
import com.example.bennytran.moviesapplication.data.MovieDbHelper;

import java.util.HashSet;

/**
 * Created by vivianso on 9/22/15.
 */
public class TestDb extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();

    void deleteTheDatabase() {
        mContext.deleteDatabase(MovieDbHelper.DATABASE_NAME);
    }

    public void setUp() {
        deleteTheDatabase();
    }

    public void testCreateDb() throws Throwable {

        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(MovieContract.MovieEntry.TABLE_NAME);

        mContext.deleteDatabase(MovieDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new MovieDbHelper(this.mContext).getWritableDatabase();

        assertEquals(true, db.isOpen());
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());


        c = db.rawQuery("PRAGMA table_info(" + MovieContract.MovieEntry.TABLE_NAME + ")", null);
        final HashSet<String> movieColumnHashSet = new HashSet<String>();
        movieColumnHashSet.add(MovieContract.MovieEntry._ID);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_TITLE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_IMAGE_URL);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_OVERVIEW);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_SCORE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_RELEASE_DATE);

        assertTrue("Error: The database doesn't contain required columns",
                movieColumnHashSet.isEmpty());
        db.close();

    }

    public void testMovieTable() {
        MovieDbHelper dbHelper = new MovieDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // ContentValues testValues;


    }

}
