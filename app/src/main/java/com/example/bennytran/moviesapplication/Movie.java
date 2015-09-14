package com.example.bennytran.moviesapplication;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by benny on 9/13/15.
 */
public class Movie {

    private Map<String, String> info;

    public Movie() {
        info = new HashMap<String, String>();
    }

    public void addProperty(String key, String value) {
        this.info.put(key, value);
    }

    public Map<String, String> getInfo() {
        return this.info;
    }



}
