package com.trion.healthcare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trion.healthcare.entity.Department;
import com.trion.healthcare.exception.DepartmentNotFoundException;
import com.trion.healthcare.repository.DepartmentRepository;
import com.trion.healthcare.service.IDepartmentManagementService;

@Service
public class DepartmentManagementImpl implements IDepartmentManagementService {

	@Autowired
	DepartmentRepository departmentRepository;

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

Department department=null;
if(departmentRepository.findById(id).isPresent()) {
	department=departmentRepository.findById(id).get();
}
else {
	throw  new DepartmentNotFoundException("Department is not found with this id"+id);
}
return department;

	}

}
