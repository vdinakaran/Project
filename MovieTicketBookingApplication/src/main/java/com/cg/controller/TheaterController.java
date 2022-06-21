package com.cg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.exception.TheaterNotFoundException;
import com.cg.model.Theater;
import com.cg.service.TheaterService;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/theatre")
public class TheaterController {
	Logger logger = LoggerFactory.getLogger(TheaterController.class);
	@Autowired
	private TheaterService theatreservice;


	/**
	 * 
	 * @return listOfTheatres
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Theater>> getAlltheatres() throws  TheaterNotFoundException {

		logger.info("-------Theatre List Fetched---------");
		return ResponseEntity.ok(theatreservice.getAllTheatres());
	}

	/**
	 * 
	 * @param t
	 * @return inserted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PostMapping("/insert")
	public ResponseEntity<Theater> addTheatre(@RequestBody Theater t)
			throws TheaterNotFoundException {

		logger.info("-------Theatre Added Successfully---------");
		return new ResponseEntity<>(theatreservice.addTheatre(t), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param t
	 * @return updated theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PutMapping("/update")
	public List<Theater> updateTheatre(@RequestBody Theater t)
			throws  TheaterNotFoundException {

		logger.info("-------Theatre Updated Successfully---------");
		return theatreservice.updateTheatre(t);
	}

	/**
	 * 
	 * @param theatreId
	 * @return theatre by theatreId
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/find/{theatreId}")
	public ResponseEntity<Theater> findTheatre(@PathVariable int theatreId)
			throws  TheaterNotFoundException {

		logger.info("-------Theatre Found with Theatre id" + theatreId + "---------");
		return ResponseEntity.ok(theatreservice.findTheatres(theatreId));
	}

	/**
	 * 
	 * @param theatreId
	 * @return deleted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@DeleteMapping("/delete/{theatreId}")
	public List<Theater> deleteMoviesById(@PathVariable int theatreId)
			throws TheaterNotFoundException {

		logger.info("-------Theatre Deleted with Theatre id" + theatreId + "---------");
		return theatreservice.deleteTheatreById(theatreId);
	}
	
	@GetMapping("/findbyMovie/{movieId}")
	public ResponseEntity<List<Theater>> findTheatreByMovieId(@PathVariable int movieId)
			throws  TheaterNotFoundException {
		return ResponseEntity.ok(theatreservice.findTheatresByMovie(movieId));
	}
	
}
