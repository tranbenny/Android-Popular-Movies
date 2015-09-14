package com.example.bennytran.moviesapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String LOG_TAG = "MainActivity";
    private ImageAdapter mImageAdapter;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // do code here


        GridView mainPage = (GridView) view.findViewById(R.id.gvContainer);
        mImageAdapter = new ImageAdapter(getActivity());
        Log.i(LOG_TAG, "loading images");
        mainPage.setAdapter(mImageAdapter);

        getMoviesTask allMovies = new getMoviesTask(mImageAdapter);
        allMovies.execute();
        Log.i(LOG_TAG, "Got all movie info");

        return view;
    }
}
