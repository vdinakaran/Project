package com.cg.service;

import com.cg.exception.UserCreationError;
import com.cg.model.User;

public interface IUserService {

	public User addUser(User user) throws UserCreationError;

	public User removeUser(User user);
}
