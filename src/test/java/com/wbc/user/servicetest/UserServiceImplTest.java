package com.wbc.user.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.wbc.user.errorhandler.InvalidUserException;
import com.wbc.user.errorhandler.UserAlreadyExistException;
import com.wbc.user.model.User;
import com.wbc.user.repository.UserRepository;
import com.wbc.user.service.Producer;
import com.wbc.user.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Mock
	private Producer producer;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	private User user;
	private Optional<User> optionalUser;
	private Optional<User> optional;
	
	@BeforeEach
	public void  setup() {
		MockitoAnnotations.openMocks(this);
		
		 user  = new User("test@gmail.com","shubham","123456");
		 optionalUser = Optional.of(user);
		 optional = Optional.empty();
	}
	
	@Test
	public void testUserAddedSuccessfull() throws Exception{
		Mockito.when(userRepository.findById("test@gmail.com")).thenReturn(optional);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User testUser = userServiceImpl.saveUser(user);
		assertEquals(testUser,user);
	}
	
	@Test
	public void testUserAddedFailedWhenUserIsNull()  {
		
		assertThrows(InvalidUserException.class, 
				() ->  userServiceImpl.saveUser(null));
		
	}
	
	@Test
	public void testUserAddedFailedUserAlreadyExist() {
		Mockito.when(userRepository.findById("test@gmail.com")).thenReturn(optionalUser);
		
		assertThrows(UserAlreadyExistException.class, 
				() ->  userServiceImpl.saveUser(user));
		
	}
	
	@Test
	public void testUserUpdatedSuccessfull() throws Exception{
		Mockito.when(userRepository.findById("test@gmail.com")).thenReturn(optionalUser);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User testUser = userServiceImpl.updateUser(user);
		assertEquals(testUser,user);
		
	}

	@Test
	public void testUserUpdatedFailed() {
		Mockito.when(userRepository.findById("test@gmail.com")).thenReturn(optional);
		assertThrows(InvalidUserException.class, 
				() ->  userServiceImpl.updateUser(user));
		
		
	}
	
	@Test
	public void  testGetUserSuccess() throws Exception{
		Mockito.when(userRepository.findByUsername("test@gmail.com")).thenReturn(user);
		User testUser = userServiceImpl.findByUsername("test@gmail.com");
		assertEquals(testUser,user);
		
	}
	
	@Test
	public void  testGetUserFailed() {
		Mockito.when(userRepository.findByUsername("test@gmail.com")).thenReturn(null);
		assertThrows(InvalidUserException.class, 
				() ->  userServiceImpl.findByUsername("test@gmail.com"));
		
	}
	
	
}
