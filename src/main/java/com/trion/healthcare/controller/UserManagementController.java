package com.trion.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.UserNotFoundException;
import com.trion.healthcare.service.IUserManagementService;

@RestController
public class UserManagementController {

	@Autowired
	IUserManagementService userManagementService;

	@GetMapping(path = "/all")
	public @ResponseBody List<Users> getAllUsers() {

		return userManagementService.findAllUsers();
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody Users user ) {
		userManagementService.saveusers(user);
		return "success";
	}
	
	@PostMapping("/update")
	public String updateUser(@RequestBody Users user ) {
		userManagementService.update(user);
		return "success";
	}
	
	@DeleteMapping("/delete")
	public String deleteUser(@RequestBody Users user ) {
		userManagementService.delete(user);
		return "success";
	}
	
	@GetMapping(path = "/getUserById/{id}")
	public @ResponseBody Users getUserById(@PathVariable Integer id) throws UserNotFoundException {

		return userManagementService.getUserById(id);
	}
	

	public IUserManagementService getUserManagementService() {
		return userManagementService;
	}

	public void setUserManagementService(IUserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}
}
