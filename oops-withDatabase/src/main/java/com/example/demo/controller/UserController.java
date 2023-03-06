package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.UserRequestClient;
import com.example.demo.custom.annotation.AuthorizeUser;
import com.example.demo.entity.User;
import com.example.demo.request.UserRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.CreateUserResponse;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRequestClient client;

	@PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addEmployee ( @RequestBody UserRequest request) {
		ResponseEntity<UserRequest>  request1 = client.sendRequest(request);
		CreateUserResponse response = null;
		if(request1.getStatusCode()== HttpStatus.OK) {
			
			 response = userService.saveUser(request1.getBody());

		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		User user = userService.getUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping(value = "/users")
	@AuthorizeUser
	public ResponseEntity<?> getUsers(@RequestParam("id") long id, HttpServletRequest servletRequest) {
		List<User> users = userService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") long id) {
		ApiResponse response = userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

}
