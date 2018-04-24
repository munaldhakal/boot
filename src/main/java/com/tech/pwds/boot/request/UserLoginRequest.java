package com.tech.pwds.boot.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserLoginRequest implements Serializable {
	@NotNull(message="username cannot be null")
	private String username;
	@NotNull(message="password cannot be null")
	private String password;
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
	
}
