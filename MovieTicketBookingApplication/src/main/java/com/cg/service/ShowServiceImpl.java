package com.cg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IScreenRepository;
import com.cg.dao.IShowRepository;
import com.cg.dao.ITheaterRepository;
import com.cg.model.Screen;
import com.cg.model.Show;
import com.cg.model.Theater;

@Service
public class ShowServiceImpl implements ShowService {
	@Autowired
	private IShowRepository showrepository;
	@Autowired
	private ITheaterRepository theatreRepository;
	@Autowired
	private IScreenRepository screenRepository;

	@Override
	public Show addShow(Show show, Integer theatreId, Integer screenId) {
		Theater theatre = new Theater();
		Screen screen = new Screen();
		if (theatreId != null) {
			theatre = theatreRepository.getOne(theatreId);
			show.setTheatre(theatre);
		}
		if (screenId != null) {
			screen = screenRepository.getOne(screenId);
			show.setScreen(screen);
		}
		showrepository.saveAndFlush(show);
		return show;
	}

	@Override
	public Show updateShow(Show show, Integer theatreId, Integer screenId) {
		Theater theatre = new Theater();
		Screen screen = new Screen();
		if (theatreId != null) {
			theatre = theatreRepository.getOne(theatreId);
			show.setTheatre(theatre);
		}
		if (screenId != null) {
			screen = screenRepository.getOne(screenId);
			show.setScreen(screen);
		}
		showrepository.saveAndFlush(show);
		return show;
	}

	@Override
	public Show removeShow(int showid) {
		Show s = showrepository.findById(showid).get();
		showrepository.delete(s);
		return s;
	}

	@Override
	public Show viewShow(int showid) {
		return showrepository.findById(showid).get();
	}

	@Override
	public List<Show> viewAllShows() {
		return showrepository.findAll();
	}

	@Override
	public List<Show> viewShowList(int theatreid) {
		return showrepository.getAllByTheatreId(theatreid);
		// return null;
	}

	@Override
	public List<Show> viewShowList(LocalDate date) {
		List<Show> shList = new ArrayList<>();
		for (Show show : showrepository.findAll()) {
			if (show.getShowDate() != null && show.getShowDate().isEqual(date)) {
				shList.add(show);
			}
		}
		return shList;
	}

}
