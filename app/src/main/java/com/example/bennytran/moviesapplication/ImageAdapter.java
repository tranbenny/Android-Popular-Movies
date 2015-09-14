package com.example.bennytran.moviesapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by benny on 9/13/15.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> movies;
    private final String LOG_TAG = "ImageAdapter";
    private LayoutInflater inflater;

    public ImageAdapter(Context c) {
        this.mContext = c;
        this.movies = new ArrayList<Movie>();
        this.inflater = LayoutInflater.from(this.mContext);
    }

    public void add(Movie movie) {
        this.movies.add(movie);
    }

    @Override
    public int getCount() {
        return this.movies.size();
    }

    @Override
    public Object getItem(int position) {
        return this.movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(LOG_TAG, "Fetching Images");
        ImageView view = (ImageView) convertView;

        if (view == null) {
            view = (ImageView) inflater.inflate(R.layout.grid_image, parent, false);
        }

        Map<String, String> movieInfo = this.movies.get(position).getInfo();
        String imagePath = "http://image.tmdb.org/t/p/w185/" + movieInfo.get("image");
        Picasso.with(mContext).load(imagePath).fit().into(view);
        return view;
    }
}
