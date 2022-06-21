package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IScreenRepository;
import com.cg.dao.ITheaterRepository;
import com.cg.exception.ScreenNotFoundException;
import com.cg.model.Screen;
import com.cg.model.Theater;

@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private IScreenRepository screenRepository;
	@Autowired
	private ITheaterRepository theatreRepository;

	/**
	 * @return screenList
	 */
	@Override
	public List<Screen> viewScreenList() throws ScreenNotFoundException {
		List<Screen> screen = screenRepository.findAll();
		if (screen.size() == 0)
			throw new ScreenNotFoundException("No screens found");
		return screen;
	}

	/**
	 * @return screen
	 */
	@Override
	public Screen addScreen(Screen screen, Integer theatreId) throws ScreenNotFoundException {
		Theater theatre = new Theater();
		if (theatreId != null) {
			if (screenRepository.existsById(screen.getScreenId())) {
				throw new ScreenNotFoundException("Screen already exists");
			} else {
				theatre = theatreRepository.getOne(theatreId);
				screen.setTheatre(theatre);
			}
			screenRepository.saveAndFlush(screen);
		}
		return screen;
	}
	@Override
	public Screen viewScreen(int screenId) throws ScreenNotFoundException {
		return screenRepository.findById(screenId).get();
		}
	/**
	 * @return updatedScreen
	 */
	@Override
	public Screen updateScreen(Screen screen, Integer theatreId) {
		Theater theatre = new Theater();
		if (theatreId != null) {
			theatre = theatreRepository.getOne(theatreId);
			screen.setTheatre(theatre);
		}
		screenRepository.saveAndFlush(screen);
		return screen;
	}

	@Override
	public Theater getTheatre(int screenId) throws ScreenNotFoundException {
		Screen screen =screenRepository.findById(screenId).get();
		Theater theatre=screen.getTheatre();
		return theatre;
	}

}
