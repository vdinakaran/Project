package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {
	// @Query("select m from Movie m join m.show s where s.theatre.theatreId = :id")
	// List<Movie> getAllByTheatreId(@Param("id") int id);

	List<Movie> getAllBymovieDate(LocalDate date);
}