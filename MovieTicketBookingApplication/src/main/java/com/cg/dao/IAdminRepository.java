package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.model.Admin;
import com.cg.model.User;

public interface IAdminRepository extends JpaRepository<User, Integer>{

}
