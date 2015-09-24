package com.example.bennytran.moviesapplication.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by benny on 9/22/15.
 */
public class MovieContract {


    public static final String CONTENT_AUTHORITY = "com.example.bennytran.moviesapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIE = "movie";


    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE)
                .build();
        // properties: title, imageURL, overview, release date, score


        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_SCORE = "score";

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }



}
