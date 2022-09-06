package com.trion.healthcare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.UserNotFoundException;
import com.trion.healthcare.service.IUserManagementService;

@RestController
public class UserManagementController {

	protected Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	IUserManagementService userManagementService;

	//@GetMapping(path = "/all")
	@RequestMapping(value = "/all", method = RequestMethod.GET)  
	public @ResponseBody List<Users> getAllUsers() {

		return userManagementService.findAllUsers();
	}

	@PostMapping("/add")
	public String addUser(@RequestBody Users user) {
		userManagementService.saveusers(user);
		return "success";
	}

	//@PostMapping("/update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)  
	public String updateUser(@RequestBody Users user) {
		userManagementService.update(user);
		return "success";
	}

	@PostMapping("/updateLastName/{id}")
	public String updateLastName(@PathVariable Integer id, @RequestBody String lastName) {
		userManagementService.updateLastName(id, lastName);
		return "success";
	}

	@PostMapping(path = "/updateUserPassword/{userName}")
	public String updateUserPassword(@PathVariable String userName, @RequestBody String password) {
		logger.info("added Uname/pword");
		userManagementService.updateUserPassword(userName, password);
		return "Successfully Added Uname/pword";
	}

	@DeleteMapping("/delete")
	public String deleteUser(@RequestBody Users user) {
		logger.info("User deleted ");
		userManagementService.delete(user);
		return "success";
	}

	@GetMapping(path = "/getUserById/{id}")
	public @ResponseBody Users getUserById(@PathVariable Integer id) throws UserNotFoundException {
		logger.debug("user entered seach string as " + id);
		return userManagementService.getUserById(id);
	}

	public IUserManagementService getUserManagementService() {
		return userManagementService;
	}

	public void setUserManagementService(IUserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}
}
