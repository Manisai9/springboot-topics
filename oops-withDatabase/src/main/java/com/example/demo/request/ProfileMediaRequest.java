package com.example.demo.request;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.enums.MediaTypes;

public class ProfileMediaRequest {

	private MediaTypes mediaType;

	private MultipartFile file;

	private Long userId;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MediaTypes getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaTypes mediaType) {
		this.mediaType = mediaType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

}
