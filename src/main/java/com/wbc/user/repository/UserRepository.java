package com.wbc.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbc.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	
	public User findByUsername(String username) ;

}
