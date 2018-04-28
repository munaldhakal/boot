package com.tech.pwds.boot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tech.pwds.boot.utilities.VisibilityStatus;

@Entity
public class UserVisibility implements Serializable {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long guardianId;
	private Double lng;
	private Double lat;
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private VisibilityStatus status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGuardianId() {
		return guardianId;
	}
	public void setGuardianId(Long guardianId) {
		this.guardianId = guardianId;
	}
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
