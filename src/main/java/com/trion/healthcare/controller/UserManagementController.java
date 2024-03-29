package com.trion.healthcare.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
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

	// @GetMapping(path = "/all")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<Users> getAllUsers() {

		return userManagementService.findAllUsers();
	}

	@PostMapping("/add")
	public String addUser(@RequestBody Users user) {
		userManagementService.saveusers(user);
		return "success";
	}

//	@PostMapping("/updateDepartment/{id}")
//	public String updateDepartment(@RequestBody Map params) {
//		logger.info("id " + params.get("id"));
//		logger.info("departmentId " + params.get("departmentId"));
//
//		userManagementService.saveusers();
//		return "success";
//	}

	@PostMapping("/updateDepartment/{id}")
	public String updateDepartment(@PathVariable Integer id, @RequestBody Map departmentid) throws Exception {
		userManagementService.updateDepartmentId((Integer) departmentid.get("departmentid"), id);
		return "Department updated ";
		// we can send request as json format
	}

	@PostMapping(path = "/search")
	public List<Users> getfirstNameAndOrlastNameme(@RequestBody Map map) throws Exception {
		return (List<Users>) userManagementService.getfirstNameAndOrlastName((String) map.get("firstName"),
				(String) map.get("lastName"));
	}

	@PostMapping(path="/criteria")
	public List<Users> getUsersByCriteria(@RequestBody Map map) throws Exception {
		return (List<Users>) userManagementService.getUsersByCriteria(map);

	}

	@PostMapping("/updateDepartmentWithInteger/{id}")
	public String updateDepartmentWithInteger(@PathVariable Integer id, @RequestBody Integer departmentid)
			throws Exception {
		userManagementService.updateDepartmentId(departmentid, id);
		return "Department updated ";
		// we can send request as Integer only
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

	// @PostMapping("/update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser(@RequestBody Users user) {
		userManagementService.update(user);
		return "success";
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

//	@PostMapping(path = "/fname/{firstName}")
//	public @ResponseBody Users getByCriteria(@PathVariable String firstName,@RequestBody String lastName) throws UserNotFoundException {
//		return  userManagementService.getByCriteria(firstName,lastName);
//	}

	public void setUserManagementService(IUserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}
}
