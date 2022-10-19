package com.wbc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wbc.user.errorhandler.InvalidUserException;
import com.wbc.user.errorhandler.UserAlreadyExistException;
import com.wbc.user.model.User;
import com.wbc.user.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	@Autowired
	private Producer producer;
	
	String kafkaTopic = "update_topic_kafka";
	String kafkaDeleteTopic = "delete_topic_kafka";

	@Override
	public User findByUsername(String username) throws Exception  {
		User user = userRepo.findByUsername(username);
		if(user != null)
			return user;
		else
			throw new InvalidUserException("Invalid user");
	}

	@Override
	public User saveUser(User user)  throws Exception {
		if(user == null) {
			throw new InvalidUserException("Invalid user");
		}
		Optional<User> isUserExists = userRepo.findById(user.getUsername());
		if(isUserExists.isEmpty()) {
			producer.sendMessagetoTopic(kafkaTopic, "user added successfully");
			return userRepo.save(user);
			
		}
		else {
			throw new UserAlreadyExistException("User already exist");
		}
		
	}

	@Override
	public void deleteUser(String username) throws Exception  {
		producer.sendMessagetoTopic(kafkaDeleteTopic, username + " has been deleted");
		userRepo.deleteById(username);
		
	}

	@Override
	public User updateUser(User user) throws Exception {
		Optional<User> isUserExists = userRepo.findById(user.getUsername());
		if(!isUserExists.isEmpty()) {
			producer.sendMessagetoTopic(kafkaTopic, user.getName()+" has been updated");
			return userRepo.save(user);
			
		}
		else {
			throw new InvalidUserException("Invalid user");
		}
	}

}
