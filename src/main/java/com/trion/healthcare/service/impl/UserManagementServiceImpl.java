package com.trion.healthcare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.UserNotFoundException;
import com.trion.healthcare.repository.UsersRepository;
import com.trion.healthcare.service.IUserManagementService;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public void saveusers(Users user) {
		usersRepository.save(user);		
	}

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
		Users user = null;
		if(usersRepository.findById(id).isPresent()) {
			 user = usersRepository.findById(id).get();
		}else {
			throw new UserNotFoundException(" noUsert found with ID "+ id);
		}
		return user;
	}

}
