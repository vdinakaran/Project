package com.cg.service;

import org.springframework.stereotype.Service;

@Service
public interface IAdminService {

	public void registerAdmin(String username, String password) throws Exception;
}