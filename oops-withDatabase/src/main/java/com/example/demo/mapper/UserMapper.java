package com.example.demo.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.example.demo.common.Constants;
import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.request.AdminRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.CreateUserResponse;

public class UserMapper {

	public static User getUser(UserRequest request, User user) {

		BeanUtils.copyProperties(request, user);
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPhoneNo(request.getPhoneNo());
		user.setEmailId(request.getEmailId());
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		return user;
	}

	public static Address getAddress(UserRequest request, Address address, User user) {

		BeanUtils.copyProperties(request, address);
		address.setAddress(request.getAddress());
		address.setState(request.getState());
		address.setCountry(request.getCountry());
		address.setUserId(user);
		address.setCreatedAt(LocalDateTime.now());
		address.setUpdatedAt(LocalDateTime.now());
		return address;
	}

	public static CreateUserResponse createUserResponse(User user) {
		CreateUserResponse response = new CreateUserResponse();
		response.setStatus(Constants.SUCCESS);
		response.setId(user.getId());
		response.setFirstName(user.getFirstName());
		response.setMessage(Constants.USER_ADDED_SUCCESSFULLY);
		return response;
	}
	
	public static Admin getAdmin(AdminRequest request, Admin admin) {
		BeanUtils.copyProperties(request, admin);
		admin.setCreatedAt(LocalDateTime.now());
		admin.setUpdatedAt(LocalDateTime.now());
		admin.setLastName(request.getLastName());
		admin.setFirstName(request.getFirstName());
		admin.setUserName(request.getUserName());
		admin.setEmailId(request.getEmailId());
		admin.setMobileNumber(request.getMobileNumber());
		
		return admin;
	}
	
	public static CreateUserResponse createAdminResponse(Admin admin) {
		CreateUserResponse response = new CreateUserResponse();
		response.setStatus(Constants.SUCCESS);
		response.setFirstName(admin.getFirstName());
		response.setMessage(Constants.ADMIN_ADDED_SUCCESSFULLY);
		return response;
	}
	
	

}
