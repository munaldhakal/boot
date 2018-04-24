package com.tech.pwds.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pwds.boot.dto.UserDto;
import com.tech.pwds.boot.model.User;
import com.tech.pwds.boot.request.UserEditRequest;
import com.tech.pwds.boot.request.UserLoginRequest;
import com.tech.pwds.boot.response.UserResponse;
import com.tech.pwds.boot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto) {
		User user = userService.createUser(userDto);
		return new ResponseEntity<Object>(user, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> editUser(@RequestBody UserEditRequest userEditRequest) {
		User user = userService.editUser(userEditRequest);
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		Map<Object,Object> res = new HashMap<Object,Object>();
		res.put("response", "Successfully Deleted");
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable Long id) {
		UserResponse userResponse=userService.getUser(id);
		return new ResponseEntity<Object>(userResponse, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllUsers() {
		List<UserResponse> userResponse=userService.getAllUsers();
		return new ResponseEntity<Object>(userResponse, HttpStatus.OK);
	}
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<Object> loginUser(@RequestBody UserLoginRequest request) {
		Long user=userService.loginUser(request);
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}
	@RequestMapping(value="/logout/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Object> logoutUser(@PathVariable Long id) {
		userService.logoutUser(id);
		Map<Object,Object> res = new HashMap<Object,Object>();
		res.put("response", "Successfully Logged Out");
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
}
