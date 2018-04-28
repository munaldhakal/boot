package com.tech.pwds.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.pwds.boot.dto.UserDto;
import com.tech.pwds.boot.dto.VisibilityDto;
import com.tech.pwds.boot.model.User;
import com.tech.pwds.boot.repository.UserRepository;
import com.tech.pwds.boot.request.UserEditRequest;
import com.tech.pwds.boot.request.UserLoginRequest;
import com.tech.pwds.boot.response.UserResponse;
import com.tech.pwds.boot.utilities.BCrypt;
import com.tech.pwds.boot.utilities.Status;
import com.tech.pwds.boot.utilities.VisibilityStatus;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VisibilityService visibilityService;

	public User createUser(@Valid UserDto userDto) {
		User user = userRepository.findByUsernameOrPhoneNumber(userDto.getUsername(), userDto.getPhoneNumber());
		if (user != null)
			throw new ServiceException("Username already exists. Please input another username");
		user = new User();
		user.setContactPerson(userDto.getContactPerson());
		user.setContactPersonNo(userDto.getContactPersonNo());
		user.setName(userDto.getName());
		user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setUsername(userDto.getUsername());
		user.setUserType(userDto.getUserType());
		user.setStatus(Status.LOGGED_OUT);
		user = userRepository.save(user);
		VisibilityDto dto = new VisibilityDto();
		dto.setLat(userDto.getLat());
		dto.setLng(userDto.getLng());
		dto.setStatus(VisibilityStatus.ON);
		visibilityService.setVisibility(user.getId(),dto);
		return user;
	}

	public User editUser(UserEditRequest userEditRequest) {
		User user = userRepository.getOne(userEditRequest.getId());
		if (user == null)
			throw new ServiceException("User doesnot exists");
		if (userEditRequest.getContactPerson() != null)
			user.setContactPerson(userEditRequest.getContactPerson());
		if (userEditRequest.getContactPersonNo() != null)
			user.setContactPersonNo(userEditRequest.getContactPersonNo());
		if (userEditRequest.getName() != null)
			user.setName(userEditRequest.getName());
		if (userEditRequest.getPassword() != null)
			user.setPassword(BCrypt.hashpw(userEditRequest.getPassword(), BCrypt.gensalt()));
		if (userEditRequest.getPhoneNumber() != null) {
			User u = userRepository.findByPhoneNumber(userEditRequest.getPhoneNumber());
			if (u == null)
				user.setPhoneNumber(userEditRequest.getPhoneNumber());
			else if (u.getId() == user.getId())
				user.setPhoneNumber(userEditRequest.getPhoneNumber());
			else
				throw new ServiceException("Phone Number Already Exists");
		}
		if (userEditRequest.getUsername() != null)
			user.setUsername(userEditRequest.getUsername());
		if (userEditRequest.getUserType() != null)
			user.setUserType(userEditRequest.getUserType());
		userRepository.save(user);
		return user;
	}

	public void deleteUser(Long id) {
		User user = userRepository.getOne(id);
		if (user == null)
			throw new ServiceException("User doesnot exists");
		userRepository.delete(user);
	}

	public UserResponse getUser(Long id) {
		User user = userRepository.getOne(id);
		if (user == null)
			throw new ServiceException("User doesnot exists");
		return getUserByObj(user);
	}

	private UserResponse getUserByObj(User user) {
		UserResponse response = new UserResponse();
		if (user.getContactPerson() != null)
			response.setContactPerson(user.getContactPerson());
		if (user.getContactPersonNo() != null)
			response.setContactPersonNo(user.getContactPersonNo());
		response.setId(user.getId());
		response.setName(user.getName());
		response.setPassword(user.getPassword());
		response.setPhoneNumber(user.getPhoneNumber());
		response.setUsername(user.getUsername());
		response.setUserType(user.getUserType());
		return response;
	}

	public List<UserResponse> getAllUsers() {
		List<UserResponse> response = new ArrayList<>();
		List<User> user = userRepository.findAll();
		if (user == null)
			throw new ServiceException("No User exists");
		for (User u : user) {
			response.add(getUserByObj(u));
		}
		return response;
	}

	public User loginUser(UserLoginRequest request) {
		User user = userRepository.findByUsername(request.getUsername());
		if (user == null)
			throw new ServiceException("No login found for username :" + request.getUsername());
		if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
			user.setStatus(Status.LOGGED_IN);
			userRepository.save(user);
		} else
			throw new ServiceException("WrongPassword");
		return user;
	}

	public void logoutUser(Long id) {
		User user = userRepository.getOne(id);
		if (user == null)
			throw new ServiceException("No user Found to logout");
		user.setStatus(Status.LOGGED_OUT);
		userRepository.save(user);
	}

}
