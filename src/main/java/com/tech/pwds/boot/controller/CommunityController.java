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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pwds.boot.dto.CommunityDto;
import com.tech.pwds.boot.response.CommunityResponse;
import com.tech.pwds.boot.service.CommunityService;

@RestController
@RequestMapping("/api/v1/community")
public class CommunityController {
	@Autowired
	private CommunityService communityService;
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object>createMessage(@RequestHeader Long userId,@RequestBody CommunityDto communityDto){
		communityService.createMessage(userId,communityDto);
		return new ResponseEntity<Object>("Successfully Created",HttpStatus.CREATED);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Object>deleteMessage(@RequestHeader Long userId,@PathVariable Long id){
		communityService.delete(userId,id);
		return new ResponseEntity<Object>("Successfully Deleted",HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object>getAllMessage(@RequestParam(required = false)String search){
		List<CommunityResponse> response = communityService.getAllMessage(search);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
}
