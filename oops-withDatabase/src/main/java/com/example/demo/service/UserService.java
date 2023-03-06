package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.request.UserRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.CreateUserResponse;

@Service
public abstract class UserService {

	public abstract CreateUserResponse saveUser(UserRequest request);
	
	public abstract User getUser(long id);
	
	public abstract List<User> getUsers();
	
	public abstract ApiResponse deleteUser(long id);
	

}
