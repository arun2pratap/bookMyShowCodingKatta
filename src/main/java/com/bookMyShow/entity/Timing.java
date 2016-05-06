package com.bookMyShow.entity;

/**
 * Created by idnasi on 5/5/2016.
 */
public class Timing {
    private String timing;
    private int availableSeats;

    public Timing(String timing, int availableSeats){
        this.timing = timing;
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void updateAvailableSeats(int seats){
        this.availableSeats = this.availableSeats - seats;
    }

    @Override
    public String toString() {
        return "Timing :: " + timing + " Available Seats :: "+ availableSeats;
    }
}
