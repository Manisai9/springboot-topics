package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.Constants;
import com.example.demo.entity.ProfileMedia;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.mapper.ProfileMediaMapper;
import com.example.demo.repository.ProfileMediaRepositoy;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.ProfileMediaRequest;
import com.example.demo.response.Response;

@Service
public class MediaService {

	private Logger logger = LoggerFactory.getLogger(MediaService.class);

	@Autowired
	private ProfileMediaRepositoy mediaRepository;

	@Autowired
	UserRepository userRepository;

	public Response uploadProfileMedia(ProfileMediaRequest request) {

		if (checkFiletype(request.getFile()) == false) {
			throw new BadRequestException(Constants.FILE_TYPE_DOESNT_MATCH);

		}
		User user = userRepository.findById(request.getUserId()).orElse(null);
		if (user == null) {
			throw new BadRequestException(Constants.NO_VALUE_PRESENT);
		}
		ProfileMedia profileMedia = ProfileMediaMapper.getMediaDetails(request.getFile());
		profileMedia.setType(request.getMediaType());
		profileMedia.setUserId(user);
		mediaRepository.save(profileMedia);
		return ProfileMediaMapper.profileMediaResponse(profileMedia);

	}

	public boolean checkFiletype(MultipartFile file) {

		String fileExtensions = ".png,.jpg,.jpeg";

		logger.info("Checking file type.");

		String fileName = file.getOriginalFilename();
		int lastIndex = fileName.lastIndexOf('.');
		String substring = fileName.substring(lastIndex, fileName.length());
		return fileExtensions.contains(substring.toLowerCase());
	}

}
