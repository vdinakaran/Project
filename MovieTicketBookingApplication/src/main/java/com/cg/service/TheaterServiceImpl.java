package com.cg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IMovieRepository;
import com.cg.dao.IScreenRepository;
import com.cg.dao.ITheaterRepository;
import com.cg.exception.TheaterNotFoundException;
import com.cg.model.Movie;
import com.cg.model.Show;
import com.cg.model.Theater;

@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private ITheaterRepository theatrerepository;
	@Autowired
	IScreenRepository screenRepository;
	@Autowired
	private IMovieRepository moviesrepository;

	@Override
	public List<Theater> getAllTheatres() throws TheaterNotFoundException {
		List<Theater> the = theatrerepository.findAll();
		//if (the.size() == 0) throw new TheatreNotFoundException("No theatres found");
		return the;
	}

	@Override
	public Theater findTheatres(int theatreId) {
		// TODO Auto-generated method stub
		if (theatrerepository.findById(theatreId).isPresent()) {
			return theatrerepository.findById(theatreId).get();
		} else
			return null;
	}

	@Override
	public Theater addTheatre(Theater m) throws TheaterNotFoundException {
		if (m != null) {
			if (theatrerepository.existsById(m.getTheatreId())) {
				throw new TheaterNotFoundException("Theatre already exists");
			} else {
				theatrerepository.saveAndFlush(m);
			}
		}
		return m;
	}

	@Override
	public List<Theater> updateTheatre(Theater m) {
		// TODO Auto-generated method stub
		theatrerepository.saveAndFlush(m);
		return theatrerepository.findAll();
	}

	@Override
	public List<Theater> deleteTheatreById(int theatreId) {
		// TODO Auto-generated method stub
		theatrerepository.deleteById(theatreId);
		return theatrerepository.findAll();
	}

	@Override
	public List<Theater> findTheatresByMovie(Integer movieId) throws TheaterNotFoundException {
		List<Theater> theatreList=new ArrayList<>();
		Movie movie=moviesrepository.findById(movieId).get();
		Integer showwID=movie.getShow().getShowId();
		List<Theater> theatres = theatrerepository.findAll();
		for(Theater theatre:theatres) {
			List<Show> shows =theatre.getShow();
			for(Show show:shows){
				if(show.getShowId()==showwID) {
					theatreList.add(theatre);
				}
			}
		}
		return theatreList;
	}

	/*
	 * @Override public Theatre addTheatre(Theatre t, List<Integer> screens) { //
	 * TODO Auto-generated method stub
	 * //if(theatrerepository.existsById(m.getTheatreId())) throws new Theatre
	 * List<Screen> preScs=new ArrayList<>(); if(screens!=null) { for(int id:
	 * screens) { Screen sc=screenRepository.getOne(id); preScs.add(sc);
	 * screenRepository.saveAndFlush(sc); } } t.setScreens(preScs);
	 * theatrerepository.saveAndFlush(t); return t; }
	 * 
	 * @Override public List<Theatre> updateTheatre(Theatre t, List<Integer>
	 * screenIds) { // TODO Auto-generated method stub return null; }
	 */

}
