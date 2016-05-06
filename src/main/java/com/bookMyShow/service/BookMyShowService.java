package com.bookMyShow.service;

import com.bookMyShow.entity.Movie;
import com.bookMyShow.entity.Screen;
import com.bookMyShow.entity.Ticket;
import com.bookMyShow.entity.Timing;
import com.bookMyShow.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idnasi on 5/5/2016.
 */
public class BookMyShowService {
    private static final int MAX_NUMBER_OF_SEATS_ALLOWED_TO_BOOK = 10;
    final List<Movie> movies = new ArrayList<Movie>();

    public Movie searchMovie(String movieName) throws  java.util.NoSuchElementException{
        return movies.stream().filter(movie->movie.getMovieName().equals(movieName)).findAny().get();
    }

    public void addMovies(List<Movie> movies){
        this.movies.addAll(movies);
    }

    public Ticket bookMovie(String movieName, int numberOfSeats, Screen screen, Timing timing) throws ValidationException {
        if(numberOfSeats > MAX_NUMBER_OF_SEATS_ALLOWED_TO_BOOK || numberOfSeats > timing.getAvailableSeats()){
            throw new ValidationException();
        }
        timing.updateAvailableSeats(numberOfSeats);
        return new Ticket(movieName,screen.getName(),numberOfSeats,timing);
    }
}
