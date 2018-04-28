package com.tech.pwds.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.pwds.boot.dto.CommunityDto;
import com.tech.pwds.boot.model.Community;
import com.tech.pwds.boot.model.User;
import com.tech.pwds.boot.repository.CommunityRepository;
import com.tech.pwds.boot.repository.UserRepository;
import com.tech.pwds.boot.response.CommunityResponse;

@Service
public class CommunityService {
	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private UserRepository userRepository;
	@Transactional
	public void createMessage(Long userId, CommunityDto communityDto) {
		Community community = new Community();
		community.setBody(communityDto.getBody());
		community.setUserId(userId);
		if(communityDto.getTitle()!=null)
			community.setTitle(communityDto.getTitle());
		communityRepository.save(community);
	}
	@Transactional
	public void delete(Long userId, Long id) {
		Optional<Community> toDelete = communityRepository.findById(id);
		if(!toDelete.isPresent())
			throw new ServiceException("Sorry no message to delete");
		if(toDelete.get().getUserId()!=userId)
			throw new ServiceException("Sorry you cannot delete message of another user");
		communityRepository.delete(toDelete.get());
	}
	@Transactional
	public List<CommunityResponse> getAllMessage(String searchId) {
		List<Community> community=null;
		if(searchId==null)
		community = communityRepository.findAll();
		else
			community=communityRepository.findAllByUserId(userRepository.findByUsername(searchId).getId());
		if(community.size()==0)
			throw new ServiceException("Sorry no message to Display");
		List<CommunityResponse> response = new ArrayList<>();
		for(Community c: community) {
			CommunityResponse com = new CommunityResponse();
			com.setId(c.getId());
			com.setBody(c.getBody());
			if(c.getTitle()!=null)
				com.setTitle(c.getTitle());
			User user = userRepository.getOne(c.getUserId());
			com.setName(user.getName());
			com.setUserType(user.getUserType()); 
			response.add(com);
		}
		return response;
	}
}
