package com.wbc.user.service;

import com.wbc.user.model.User;

public interface UserService {
	
	public User findByUsername(String username) throws Exception ;
	
	public User saveUser(User user) throws Exception ;
	
	public void deleteUser(String username) throws Exception ;
	
	public User updateUser(User user)throws Exception ;

}
