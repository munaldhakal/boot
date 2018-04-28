package com.tech.pwds.boot.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.pwds.boot.dto.VisibilityDto;
import com.tech.pwds.boot.model.User;
import com.tech.pwds.boot.model.UserVisibility;
import com.tech.pwds.boot.repository.UserRepository;
import com.tech.pwds.boot.repository.VisibilityRepository;
import com.tech.pwds.boot.response.LocationResponse;
import com.tech.pwds.boot.utilities.VisibilityStatus;

@Service
public class VisibilityService {
	@Autowired
	private VisibilityRepository visibilityRepository;
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void setVisibility(Long userId, VisibilityDto dto) {
		if (userId == null)
			throw new ServiceException("Please login first");
		Optional<UserVisibility> visibility = visibilityRepository.findById(userId);
		UserVisibility toCreate = null;
		if (visibility.isPresent())
			toCreate = visibility.get();
		else
			toCreate = new UserVisibility();
		if (dto.getLat() != null)
			toCreate.setLat(dto.getLat());
		if (dto.getLng() != null)
			toCreate.setLng(dto.getLng());
		if (dto.getStatus() != null)
			toCreate.setStatus(dto.getStatus());
		toCreate.setUserId(userId);
		if (toCreate.getGuardianId() == null) {
			User user = userRepository.getOne(userId);
			if (user.getContactPersonNo() != null) {
				User guardian = userRepository.findByPhoneNumber(user.getContactPersonNo());
				if (guardian != null)
					toCreate.setGuardianId(guardian.getId());
			}
		}
		visibilityRepository.save(toCreate);
	}
	@Transactional
	public List<LocationResponse> getLocations(Double lat, Double lng) {
		List<UserVisibility> vis = visibilityRepository.findAllByStatus(VisibilityStatus.ON);
		if(vis.size()==0)
			throw new ServiceException("No users are active right now");
		List<LocationResponse>response = new ArrayList<>();
		for(UserVisibility uv:vis) {
			LocationResponse visible = new LocationResponse();
			visible.setId(uv.getId());
			Double lat1=uv.getLat();
			Double lng1=uv.getLng();
			visible.setLat(String.valueOf(lat1));
			visible.setLng(String.valueOf(lng1));
			visible.setUsername(userRepository.getOne(uv.getUserId()).getUsername());
			visible.setDistance(BigDecimal.valueOf(calculateDistance(lat,lng,lat1,lng1)).toPlainString());
			response.add(visible);
		}
		return response;
	}
	
	private Double calculateDistance(Double lat,Double lng,Double lat1,Double lng1) {
		final int R = 6371; // Radius of the earth
	    Double latDistance = Math.toRadians(lat1 - lat);
	    Double lonDistance = Math.toRadians(lng1 - lng);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(lat1))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    Double distance = R * c * 1000; // convert to meters
		return distance;
	}

}
