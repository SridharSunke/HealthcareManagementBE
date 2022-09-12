package com.trion.healthcare.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trion.healthcare.entity.Department;
import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.DepartmentNotFoundException;
import com.trion.healthcare.exception.UserNotFoundException;
import com.trion.healthcare.repository.DepartmentRepository;
import com.trion.healthcare.repository.UsersRepository;
import com.trion.healthcare.service.IUserManagementService;

@Service
public class UserManagementServiceImpl implements IUserManagementService {
	protected Logger logger = LoggerFactory.getLogger(getClass().getName());
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public void saveusers(Users user) {
		usersRepository.save(user);
	}

	@Override
	public List<Users> findAllUsers() {
		return (List<Users>) usersRepository.findAll();
	}

	@Override
	public void delete(Users user) {
		usersRepository.delete(user);
	}

	@Override
	public void update(Users user) {
		usersRepository.save(user);

	}

	@Override
	public Users getUserById(Integer id) throws UserNotFoundException {
		logger.info("User tying to search with ID " + id);
		Users user = null;
		if (usersRepository.findById(id).isPresent()) {// existsById(id))
			user = usersRepository.findById(id).get();
		} else {
			logger.error(" no User was found with ID " + id);
			throw new UserNotFoundException(" noUsert found with ID " + id);
		}
		return user;
	}

	@Override
	public void updateLastName(Integer id, String lastName) {
		try {
			logger.info("receive last name update for id " + id + " value " + lastName);
			Users user = getUserById(id);
			user.setLastName(lastName);
			usersRepository.save(user);
		} catch (UserNotFoundException e) {
			logger.error("error while updating last name " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Users updateUserPassword(String userName, String password) {
		Users user = null;
		if (usersRepository.findByUserName(userName).isPresent()) {
			user = usersRepository.findByUserName(userName).get();
		} else {
			logger.info(" no User was found with userName " + userName);
			user = new Users();
			user.setUserName(userName);
			user.setFirstName(userName);
		}

		user.setPassword(password);
		usersRepository.save(user);
		return null;
	}

	@Override
	public void updateDepartmentId(Integer departmentid, Integer id) throws Exception {
		Users user = null;
		Department department = null;
		if (usersRepository.findById(id).isPresent()) {
			user = usersRepository.findById(id).get();

		} else {
			logger.error("User  not mateched with given user id " + id);
			throw new UserNotFoundException("User  not mateched with given user id" + id);
		}

		if (departmentRepository.existsById(departmentid)) {
			department = departmentRepository.findById(departmentid).get();
		} else {
			logger.error("Departement not matched with given Department id" + departmentid);
			throw new DepartmentNotFoundException("Departement not matched with given Department id" + departmentid);
		}

		user.setDepartmentId(department);
		usersRepository.save(user);
	}
}
