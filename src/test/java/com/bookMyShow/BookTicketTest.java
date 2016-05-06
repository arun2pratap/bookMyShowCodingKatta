package com.bookMyShow;

import com.bookMyShow.entity.Movie;
import com.bookMyShow.entity.Screen;
import com.bookMyShow.entity.Ticket;
import com.bookMyShow.entity.Timing;
import com.bookMyShow.exception.ValidationException;
import com.bookMyShow.service.BookMyShowService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by idnasi on 5/5/2016.
 */
public class BookTicketTest {
    final BookMyShowService bookMyShowService =  new BookMyShowService();

    @Before
    public void setup(){
        Timing morning = new Timing("10:00",20);
        Timing  afternoon = new Timing("12:00",20);
        Timing evening = new Timing("6:00",20);

        List<Timing> timings = new LinkedList<>();
        timings.add(morning);
        timings.add(afternoon);
        timings.add(evening);

        Screen screenA = new Screen("screenA",20,  timings);
        Screen screenB = new Screen("screenB",20,  timings);
        Screen screenC = new Screen("screenC",20,  timings);

        final List<Movie> movies = new ArrayList<Movie>();
        Map<Screen,List<Timing>> currentlyShownAt = new TreeMap<>();
        currentlyShownAt.put(screenA,timings);
        currentlyShownAt.put(screenB,timings);
        currentlyShownAt.put(screenC,timings);

        movies.add(new Movie("Don",currentlyShownAt));
        bookMyShowService.addMovies(movies);
    }
    @Test
    public void searchMovie(){
        Movie movie = bookMyShowService.searchMovie("Don");
        Assert.assertEquals("Don",movie.getMovieName());
    }

    @Test(expected=java.util.NoSuchElementException.class)
    public void shouldThrowExceptionIfMovieDoesNotExist(){
        Movie movie = bookMyShowService.searchMovie("Movie which does not exist");
    }

    @Test
    public void searchMovieWithTiming(){
        Movie movie = bookMyShowService.searchMovie("Don");
        final Map<Screen, List<Timing>> currentlyShownAt = movie.getCurrentlyShownAt();
        Assert.assertEquals(3, currentlyShownAt.size());
        Assert.assertEquals("Don",movie.getMovieName());
    }

    @Test
    public void bookMovie() throws ValidationException {
        Movie movie = bookMyShowService.searchMovie("Don");
        Map<Screen, List<Timing>> currentlyShownAt = movie.getCurrentlyShownAt();
        Map.Entry<Screen, List<Timing>> entry = currentlyShownAt.entrySet().iterator().next();

        Ticket ticket = bookMyShowService.bookMovie(movie.getMovieName(), 10, entry.getKey(), entry.getValue().get(0));
        Assert.assertEquals("Movie Name: Don Screen Name: screenA Number Of Seats: 10 Timing: Timing :: 10:00 Available Seats :: 10",ticket.toString());
    }

    @Test(expected = ValidationException.class)
    public void shouldNotAllowToBookMorethan10Tickets() throws ValidationException {
        Movie movie = bookMyShowService.searchMovie("Don");
        Map<Screen, List<Timing>> currentlyShownAt = movie.getCurrentlyShownAt();
        Map.Entry<Screen, List<Timing>> entry = currentlyShownAt.entrySet().iterator().next();

        Ticket ticket = bookMyShowService.bookMovie(movie.getMovieName(), 20, entry.getKey(), entry.getValue().get(0));
    }


    @Test(expected = ValidationException.class)
    public void shouldNotOverbook() throws ValidationException {
        Movie movie = bookMyShowService.searchMovie("Don");
        Map<Screen, List<Timing>> currentlyShownAt = movie.getCurrentlyShownAt();
        Map.Entry<Screen, List<Timing>> entry = currentlyShownAt.entrySet().iterator().next();
        bookMyShowService.bookMovie(movie.getMovieName(), 10, entry.getKey(), entry.getValue().get(0));
        bookMyShowService.bookMovie(movie.getMovieName(), 10, entry.getKey(), entry.getValue().get(0));
        bookMyShowService.bookMovie(movie.getMovieName(), 10, entry.getKey(), entry.getValue().get(0));
        bookMyShowService.bookMovie(movie.getMovieName(), 10, entry.getKey(), entry.getValue().get(0));
    }
}
