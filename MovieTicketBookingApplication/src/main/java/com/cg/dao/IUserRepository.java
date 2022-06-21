package com.cg.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.model.User;

public interface IUserRepository extends JpaRepository<User, Integer>{

}
