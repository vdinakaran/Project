package com.cg.service;

import java.util.List;

import com.cg.exception.ScreenNotFoundException;
import com.cg.model.Screen;
import com.cg.model.Theater;

public interface ScreenService {
	public Screen addScreen(Screen screen, Integer theatreId) throws ScreenNotFoundException;
	public List<Screen> viewScreenList() throws ScreenNotFoundException;
	public Screen updateScreen(Screen s, Integer theatreId);
	public Screen viewScreen(int screenId) throws ScreenNotFoundException;
	public Theater getTheatre(int screenId) throws ScreenNotFoundException;
}
