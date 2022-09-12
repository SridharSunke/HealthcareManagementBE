package com.trion.healthcare.service;

import java.util.List;
import java.util.Map;

import com.trion.healthcare.entity.Department;
import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.DepartmentNotFoundException;

public interface IDepartmentManagementService {

	public List<Department> findAllDepartment();

	void saveDepartment(Department department);

	void updateDepartment(Department department);

	void deleteDepartment(Department department);

	Department getDepartmentById(Integer id) throws DepartmentNotFoundException;

	public Map<Department, List<Users>> getDepartmentDoctors();
}
