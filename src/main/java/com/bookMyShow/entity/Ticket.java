package com.bookMyShow.entity;

/**
 * Created by idnasi on 5/5/2016.
 */
public class Ticket {
    private final String movieName;
    private final String screenName;
    private final int numberOfSeats;
    private final Timing timing;

    public Ticket(String movieName, String screenName, int numberOfSeats, Timing timing) {
        this.movieName = movieName;
        this.screenName = screenName;
        this.numberOfSeats = numberOfSeats;
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "Movie Name: "+movieName+" Screen Name: "+screenName+" Number Of Seats: "+numberOfSeats+" Timing: "+timing;
    }
}
