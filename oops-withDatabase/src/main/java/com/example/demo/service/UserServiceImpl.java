package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.CreateUserResponse;

@Service
public class UserServiceImpl extends UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;

	public CreateUserResponse saveUser(UserRequest request) {

		Optional<User> userOptional = userRepository.findByEmailId(request.getEmailId());
		if (userOptional.isPresent()) {
			throw new BadRequestException(Constants.USER_ALREADY_PRESENT);
		}

		User user = UserMapper.getUser(request, new User());
		userRepository.save(user);
		Address address = UserMapper.getAddress(request, new Address(), user);
		addressRepository.save(address);
		return UserMapper.createUserResponse(user);
	}

	public User getUser(long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new BadRequestException(Constants.NO_VALUE_PRESENT);
		}
		return user;
	}

	public List<User> getUsers() {

		return userRepository.findAll();
	}

	public ApiResponse deleteUser(long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new BadRequestException(Constants.NO_VALUE_PRESENT);
		}
		return new ApiResponse(Constants.SUCCESS, Constants.DELETED_SUCCESSFULLY);
	}


}
