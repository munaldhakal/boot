package com.tech.pwds.boot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class CommunityDto implements Serializable {
	@NotNull(message ="Please input message")
	private String body;
	private String title;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
