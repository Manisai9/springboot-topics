package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.AdminRequest;
import com.example.demo.response.CreateUserResponse;
import com.example.demo.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping(value = "/addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateUserResponse> addAdmin(@RequestBody AdminRequest request) {
		CreateUserResponse response = adminService.saveAdmin(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
