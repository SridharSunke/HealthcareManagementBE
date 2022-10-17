package com.trion.healthcare.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	EntityManager entityManager;

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
//
//	@Override
//	public Users getByCriteria(String firstName, String lastName) throws UserNotFoundException {
//		Users user = null;
//		if (usersRepository.findByCriteria(firstName, lastName) != null) {// existsById(id))
//			user = usersRepository.findByCriteria(firstName, lastName);
//		} else {
//			logger.error(" no User was found with ID " + firstName);
//			throw new UserNotFoundException(" noUsert found with ID " + firstName);
//		}
//		return user;
//	}

//	@Override
//	public List<Users> getfirstNameAndOrlastName(String firstName, String lastName) throws UserNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Users getByfirstName(String firstname) throws UserNotFoundException {
//Users user = null;
//if(usersRepository.findByfirstName(firstname).isPresent)
//	}

	@Override
	public List<Users> getfirstNameAndOrlastName(String firstName, String lastName) throws UserNotFoundException {
		List<Users> list = usersRepository.findByfirstNameAndOrlastName(firstName, lastName);
		if (list.size() > 0) {
			return list;
		} else {

			return new ArrayList<Users>();
		}
	}

	@Override
	public List<Users> getfirstNameOrlastName(String firstName, String lastName) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> getUsersByCriteria(Map map) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		 CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
	      Root<Users> users = query.from(Users.class);
	      query.select(users);
	      List<Predicate> whereClause = new ArrayList<>();
	      
	      if(map.get("firstName")!= null ) {
	    	  whereClause.add(criteriaBuilder.like(users.get("firstName"), "%" + (String)map.get("firstName")+"%"));
	      }
	      if(map.get("lastName")!= null) {
	    	  whereClause.add(criteriaBuilder.like(users.get("lastName"), "%" + (String)map.get("lastName") + "%"));
	      }
	      if(map.get("departmentId")!= null && map.get("departmentId") != "") {
	    	  whereClause.add(criteriaBuilder.equal(users.get("departmentId").get("id"), new Long((String)map.get("departmentId"))));
	      }
	      
	      query.where(criteriaBuilder.and(whereClause.toArray(new Predicate[whereClause.size()])));
	      
	      List<Users> lusers = entityManager.createQuery(query).getResultList();
	      entityManager.close();
		return lusers;
	}

	
	
	
//	@Override
//	public List<Users> getfirstNameOrlastName(String firstName, String lastName) throws UserNotFoundException {
//		
//		
//		
//		List<Users> l = usersRepository.findByfirstNameOrlastName(firstName, lastName);
//		if (l.size() > 0) {
//			return l;
//		} else {
//
//			return new ArrayList<Users>();
//		}
//	}
}
//	@Override
//	public Users getByCriteria(String firstName, String lastName) throws UserNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//}
//	@Override
//	public Users getByfirstName(String firstname) throws UserNotFoundException {
//		Users user = null;
//		if (usersRepository.findByfirstName(firstname).isPresent) {
//			user = usersRepository.findByfirstName(firstname).get();
//		}
//
//		return null;
//	}

//public Users getByfirstName(String firstname) throws UserNotFoundException {
//	List<Users> list = usersRepository.findByfirstNameAndOrlastName(firstName, lastName);
//	if (list.size() > 0) {
//		return list;
//	} else {
//		return new ArrayList<Users>();
//	}
//}

//	public List<Users> getByfirstNameAndOrlastName(String firstname, String lastName) throws UserNotFoundException
//
//	{
//		List<Users> list = usersRepository.findByfirstNameAndOrlastName(firstname, lastName);
//		if (list.size() > 0) {
//			return list;
//		} else {
//			return new ArrayList<Users>();
//		}

//	}
