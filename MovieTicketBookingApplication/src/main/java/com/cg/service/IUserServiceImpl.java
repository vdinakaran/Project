package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IUserRepository;
import com.cg.dao.QueryClass;
import com.cg.exception.UserCreationError;
import com.cg.model.User;
import com.cg.validator.InputValidator;

@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userrepo;

	@Autowired
	InputValidator validate;

	@Autowired
	QueryClass qcp;

	@Override
	public User addUser(User user) throws UserCreationError {
		if (!validate.usernameValidator(user.getUsername()))
			throw new UserCreationError("Check Username !!!!");
		if (!validate.passwordValidator(user.getPassword()))
			throw new UserCreationError("Cannot register this User with this password");
		return userrepo.saveAndFlush(user);
	}

	@Override
	public User removeUser(User user) {
		userrepo.delete(user);
		return user;
	}

}
