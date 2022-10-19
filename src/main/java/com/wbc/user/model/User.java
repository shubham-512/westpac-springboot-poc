package com.wbc.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private String username;
	private String name;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
		super();
	}
	public User(String username, String name, String password) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", password=" + password + "]";
	}
	

}
