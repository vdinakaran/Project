package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.exception.MovieNotFoundException;
import com.cg.model.Movie;

public interface MovieService {

	public Movie addMovie(Movie movie) throws MovieNotFoundException;

	public Movie removeMovie(int movieid) throws MovieNotFoundException;
	
	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	public Movie addMovieToShow(Movie movie, Integer showId) throws MovieNotFoundException;

	public Movie viewMovie(int movieid) throws MovieNotFoundException;

	public List<Movie> viewMovieList() throws MovieNotFoundException;

	public List<Movie> viewMovieList(int theatreid);

	public List<Movie> viewMovieList(LocalDate date);
}
