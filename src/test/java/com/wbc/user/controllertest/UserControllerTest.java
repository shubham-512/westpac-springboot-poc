package com.wbc.user.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbc.user.controller.UserController;
import com.wbc.user.errorhandler.InvalidUserException;
import com.wbc.user.model.User;
import com.wbc.user.service.UserServiceImpl;

@WebMvcTest
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userServiceImpl;
	
	@InjectMocks
	private UserController userController;
	
	private User user;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
		mapper.findAndRegisterModules();
		
		 user  = new User("test@gmail.com","shubham","123456");
	}
	
	@Test
	public void testAddUserSuccess() throws Exception{
		when(userServiceImpl.saveUser(user)).thenReturn(user);
		mockMvc.perform(post("/api/v1/addUser").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user))).andExpect(status().isCreated()).andDo(print());
			
	}
	
	@Test
	public void testAddUserFailure() throws Exception{
		when(userServiceImpl.saveUser(user)).thenThrow(new InvalidUserException("Invalid user"));
		mockMvc.perform(post("/api/v1/addUser").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(null))).andExpect(status().isBadRequest()).andDo(print());
			
	}
	
	@Test
	public void testgetUserSuccess() throws Exception{
		when(userServiceImpl.findByUsername("test@gmail.com")).thenReturn(user);
		mockMvc.perform(get("/api/v1/getUser/test@gmail.com").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user))).andExpect(status().isOk()).andDo(print());
			
	}
	
	@Test
	public void testDeleteUserSuccess() throws Exception{
		
		mockMvc.perform(delete("/api/v1/deleteUser/test@gmail.com").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user))).andExpect(status().isOk()).andDo(print());
			
	}
	
	@Test
	public void testUpdateUserSuccess() throws Exception{
		when(userServiceImpl.updateUser(user)).thenReturn(user);
		mockMvc.perform(put("/api/v1/updateUser").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user))).andExpect(status().isOk()).andDo(print());
			
	}
	
	


}
