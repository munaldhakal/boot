package com.tech.pwds.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pwds.boot.dto.VisibilityDto;
import com.tech.pwds.boot.response.LocationResponse;
import com.tech.pwds.boot.service.VisibilityService;

@RestController
@RequestMapping("/api/v1/visibility")
public class VisibilityController {
	@Autowired
	private VisibilityService visibilityService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object>setVisibility(@RequestHeader Long userId,@RequestBody VisibilityDto dto){
		visibilityService.setVisibility(userId,dto);
		return new ResponseEntity<Object>("Successfully set the visibility",HttpStatus.CREATED);
	}
	@RequestMapping(value= "/{lat}/{lng}",method = RequestMethod.GET)
	public ResponseEntity<Object> getLocations(@PathVariable String lat,@PathVariable String lng){
		List<LocationResponse> response = visibilityService.getLocations(Double.valueOf(lat),Double.valueOf(lng));
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
}
