package com.tech.pwds.boot.dto;

import java.io.Serializable;

import com.tech.pwds.boot.utilities.VisibilityStatus;

public class VisibilityDto implements Serializable {
	private Double lng;
	private Double lat;
	private VisibilityStatus status;

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public VisibilityStatus getStatus() {
		return status;
	}

	public void setStatus(VisibilityStatus status) {
		this.status = status;
	}

}
