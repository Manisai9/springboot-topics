package com.example.demo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.demo.utils.OptionalPattern;

public class UserRequest {

	@NotBlank(message = "First name is mandatory")
	@Pattern(message = "Provide valid first name", regexp = "^[ A-Za-z]*$")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	@Pattern(message = "Provide valid last name", regexp = "^[ A-Za-z]*$")
	private String lastName;

	@OptionalPattern(message = "Provide valid Mobile number", regexp = "^\\+[1-9]{1}[0-9]{3,14}$")
	private String phoneNo;

	@OptionalPattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Invalid Email id")
	@Email
	private String emailId;
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	@NotBlank(message = "City is mandatory")
	@Pattern(message = "Provide valid state name", regexp = "^[ A-Za-z]*$")
	private String state;

	@NotBlank(message = "Country is mandatory")
	@Pattern(message = "Provide valid country name", regexp = "^[ A-Za-z]*$")
	private String country;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
