package com.example.bennytran.moviesapplication;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by benny on 9/13/15.
 */
public class getMoviesTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

    private final String LOG_TAG = "AsyncMovieTask";
    private ArrayList<Movie> movies;
    private ImageAdapter mAdapter;

    public getMoviesTask(ImageAdapter mAdapter) {
        movies = new ArrayList<Movie>();
        this.mAdapter = mAdapter;
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        if (result == null) {
            return;
        }
        for (int i = 0; i < result.size(); i++) {
            this.mAdapter.add(result.get(i));
        }
    }



    @Override
    protected ArrayList<Movie> doInBackground(Void... params) {
        return this.findPopularMovies();
    }

    private ArrayList<Movie> findPopularMovies() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String movieJsonStr = null;
        String baseURL = "http://api.themoviedb.org/3/discover/movie?";
        String sortByParam = "sort_by";
        String keyParam = "api_key";
        apiKey key = new apiKey();
        String apiKey = key.getKey();

        try {
            Uri builtUri = Uri.parse(baseURL).buildUpon()
                    .appendQueryParameter(sortByParam, "popularity.desc")
                    .appendQueryParameter(keyParam, apiKey)
                    .build();
            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(20 * 1000);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            movieJsonStr = buffer.toString();
            Log.i(LOG_TAG, "JSON data: " + movieJsonStr);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Url error ", e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(LOG_TAG, "IO Error ", e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch( IOException e) {
                    Log.e(LOG_TAG, "Error closing stream ", e);
                }
            }
        }

        try {
            return processJson(movieJsonStr);
        } catch(JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Movie> processJson(String jsonData) throws JSONException {

        JSONObject movieJson = new JSONObject(jsonData);
        JSONArray resultArray = movieJson.getJSONArray("results");

        ArrayList<Movie> result = new ArrayList<Movie>();

        for (int i = 0; i < resultArray.length(); i++) {
            JSONObject movie = resultArray.getJSONObject(i);
            Movie movieInfo = new Movie();
            movieInfo.addProperty("title", movie.getString("title"));
            movieInfo.addProperty("image", movie.getString("poster_path"));
            movieInfo.addProperty("overview", movie.getString("overview"));
            movieInfo.addProperty("score", "" + movie.getDouble("vote_average"));
            movieInfo.addProperty("date", movie.getString("release_date"));
            Log.i(LOG_TAG, "Movie: " + movie.getString("title"));
            result.add(movieInfo);
        }
        return result;
    }

}
