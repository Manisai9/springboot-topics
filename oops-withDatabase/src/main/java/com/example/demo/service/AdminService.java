package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.entity.Admin;
import com.example.demo.exception.BadRequestException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.AdminRepository;
import com.example.demo.request.AdminRequest;
import com.example.demo.response.CreateUserResponse;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	public CreateUserResponse saveAdmin(AdminRequest request) {

		Optional<Admin> userOptional = adminRepository.findByEmailId(request.getEmailId());
		if (userOptional.isPresent()) {
			throw new BadRequestException(Constants.ADMIN_ALREADY_PRESENT);
		}

		Admin admin = UserMapper.getAdmin(request, new Admin());
		adminRepository.save(admin);

		return UserMapper.createAdminResponse(admin);

	}
}
