package com.tech.pwds.boot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.tech.pwds.boot.utilities.Status;
import com.tech.pwds.boot.utilities.UserType;

public class UserDto implements Serializable {
	@NotNull(message="Name is required")
	private String name;
	@NotNull(message="UserName is required")
	private String username;
	@NotNull(message="Password is required")
	private String password;
	@NotNull(message="PhoneNumber is required")
	private String phoneNumber;
	private String contactPerson;
	private String contactPersonNo;
	@NotNull(message="UserType is required")
	private UserType userType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonNo() {
		return contactPersonNo;
	}

	public void setContactPersonNo(String contactPersonNo) {
		this.contactPersonNo = contactPersonNo;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
