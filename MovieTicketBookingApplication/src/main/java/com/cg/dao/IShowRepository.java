package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.model.Show;

public interface IShowRepository extends JpaRepository<Show, Integer>{
	@Query("select s from Show s where s.theatre.theatreId = :id")
	List<Show> getAllByTheatreId(@Param("id") int id);
}
