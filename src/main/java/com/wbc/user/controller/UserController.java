package com.wbc.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wbc.user.model.User;
import com.wbc.user.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Map<String, String> map = new HashMap<>();
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user)throws Exception {
		return userService.saveUser(user);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/getUser/{userName}")
	public User getUser(@PathVariable String userName)throws Exception  {
		return userService.findByUsername(userName);
		
	}
	
	
	@DeleteMapping("/deleteUser/{userName}")
	public ResponseEntity<?> deleteUserByUserName(@PathVariable String userName) throws Exception {
		userService.deleteUser(userName);
		map.clear();
		map.put("message ", "user has been removed");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) throws Exception  {
		return userService.updateUser(user);
	}

}
