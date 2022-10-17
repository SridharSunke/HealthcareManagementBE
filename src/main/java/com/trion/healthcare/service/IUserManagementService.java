package com.trion.healthcare.service;

import java.util.List;
import java.util.Map;

import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.UserNotFoundException;

public interface IUserManagementService {
	void saveusers(Users user);

	public List<Users> findAllUsers();

	void delete(Users user);

	void update(Users user);

	Users getUserById(Integer id) throws UserNotFoundException;

	// Users getByCriteria(String firstName,String lastName) throws
	// UserNotFoundException;

	void updateLastName(Integer id, String lastName);

	Users updateUserPassword(String userName, String password);

	void updateDepartmentId(Integer departmentid, Integer id) throws Exception;

	// Users getByfirstName(String firstname) throws UserNotFoundException;

	List<Users> getfirstNameAndOrlastName(String firstName, String lastName) throws UserNotFoundException;

	List<Users> getfirstNameOrlastName(String firstName, String lastName) throws UserNotFoundException;

	List<Users> getUsersByCriteria(Map map);
}