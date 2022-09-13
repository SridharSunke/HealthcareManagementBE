package com.trion.healthcare.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trion.healthcare.entity.Department;
import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.DepartmentNotFoundException;
import com.trion.healthcare.repository.DepartmentRepository;
import com.trion.healthcare.repository.UsersRepository;
import com.trion.healthcare.service.IDepartmentManagementService;

@Service
public class DepartmentManagementImpl implements IDepartmentManagementService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	UsersRepository usersRepository;

	@Override
	public List<Department> findAllDepartment() {

		return (List<Department>) departmentRepository.findAll();
	}

	@Override
	public void saveDepartment(Department department) {
		departmentRepository.save(department);

	}

	@Override
	public void updateDepartment(Department department) {
		departmentRepository.save(department);

	}

	@Override
	public void deleteDepartment(Department department) {
		departmentRepository.delete(department);

	}

	@Override
	public Department getDepartmentById(Integer id) throws DepartmentNotFoundException {

		Department department = null;
		if (departmentRepository.findById(id).isPresent()) {
			department = departmentRepository.findById(id).get();
		} else {
			throw new DepartmentNotFoundException("Department is not found with this id" + id);
		}
		return department;

	}

	@Override
	public Map<Department, List<Users>> getDepartmentDoctors() {
		Map<Department, List<Users>> data = new HashMap<>();
		
//	//===========================================================================
//		//Approach 1
//		Iterable<Users> iteratable = usersRepository.findAll();
//		Iterator<Users> it = iteratable.iterator();
//		while(it.hasNext()) {
//			Users user = it.next();
//			if(data.get(user.getDepartmentId()) == null){
//				  List<Users> userList = new
//				  ArrayList<Users>(); userList.add(user);
//				  data.put(user.getDepartmentId(),userList );
//			}else { List<Users> userList =
//				  data.get(user.getDepartmentId()); userList.add(user);
//			}
//		}
	//==========================================================================
//		// Reading the values 
//		Set<Department> depSet =  data.keySet();
//		Iterator<Department> depIt = depSet.iterator();
//		
//		
//		while(depIt.hasNext()) {
//			Department dp= depIt.next();
//			List<Users> ul = data.get(dp);
//			System.out.println("department : "+ dp.getName());
//			System.out.println("--------");
//			for (Users users : ul) {
//				System.out.println("Doctor Name : " + users.getFirstName() + " " + users.getLastName());
//			}
//			
//			System.out.println("=========");
//			
//		}
		//===========================================================================
		//approach 2
		
		  usersRepository.findAll().forEach(user -> {
		  if(data.get(user.getDepartmentId()) == null){ 
			List<Users> userList = new ArrayList<Users>();
			userList.add(user);
		  data.put(user.getDepartmentId(),userList );
		  
		  }else { List<Users> userList =
		  data.get(user.getDepartmentId());
		  userList.add(user); 
		  } });
		 
		return data;
	}


}
