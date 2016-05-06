package com.bookMyShow.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movie {

    private String movieName;
    Map<Screen,List<Timing>> currentlyShownAt = new HashMap<>();

    public Movie(String movieName,  Map<Screen, List<Timing>> currentlyShownAt) {
        this.movieName = movieName;
        this.currentlyShownAt = currentlyShownAt;
    }

    public String getMovieName() {
        return movieName;
    }

    public Map<Screen, List<Timing>> getCurrentlyShownAt() {
        currentlyShownAt.forEach((k,v)->System.out.println("Screen : " + k +  v));
        return currentlyShownAt;
    }
}
