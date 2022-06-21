package com.cg.service;

import java.util.List;

import com.cg.exception.TheaterNotFoundException;
import com.cg.model.Theater;

public interface TheaterService {
	public List<Theater> getAllTheatres() throws TheaterNotFoundException;

	public Theater findTheatres(int theatreId);

	public Theater addTheatre(Theater t) throws TheaterNotFoundException;

	public List<Theater> updateTheatre(Theater t);

	public List<Theater> deleteTheatreById(int theatreId);
	
	public List<Theater> findTheatresByMovie(Integer movieId) throws TheaterNotFoundException;
}
