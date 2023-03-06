package com.example.demo.enums;

public enum MediaTypes {
	
	USER_PROFILE(0),ADMIN_PROFILE(1);
	
	private int mediaType;

	private MediaTypes(int mediaType) {
		this.mediaType = mediaType;
	}

	public int getMediaType() {
		return mediaType;
	}

}
