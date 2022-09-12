package com.trion.healthcare.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trion.healthcare.entity.Department;
import com.trion.healthcare.entity.Users;
import com.trion.healthcare.exception.DepartmentNotFoundException;
import com.trion.healthcare.service.IDepartmentManagementService;

@RestController
@RequestMapping("department")
public class DepartmentController {

	@Autowired
	IDepartmentManagementService iDepartmentManagementService;

	public IDepartmentManagementService getiDepartmentManagementService() {
		return iDepartmentManagementService;
	}

	public void setiDepartmentManagementService(IDepartmentManagementService iDepartmentManagementService) {
		this.iDepartmentManagementService = iDepartmentManagementService;
	}

	@GetMapping(path = "/getall")
	public @ResponseBody List<Department> getAllDepartment() {

		return iDepartmentManagementService.findAllDepartment();

	}
	
	@GetMapping(path = "/getDepartmentDoctors")
	public @ResponseBody Map<Department, List<Users>> getDepartmentDoctors() {

		return iDepartmentManagementService.getDepartmentDoctors();

	}


	@PostMapping(path = "/update")
	public String updateDepartment(@RequestBody Department department) {

		iDepartmentManagementService.updateDepartment(department);
		return "Successfully Updated";
	}

	@PutMapping(path = "/add")
	public String saveDepartment(@RequestBody Department department) {
		iDepartmentManagementService.saveDepartment(department);
		return "Successfully Added";
	}

	@DeleteMapping(path = "/delete")
	public String deleteDepartment(@RequestBody Department department) {
		iDepartmentManagementService.deleteDepartment(department);
		return "Successfully Deleted";

	}
@GetMapping(path="/getById/{id}")
	public @ResponseBody Department getAllDepartmentById(@PathVariable Integer id) throws DepartmentNotFoundException {
		return iDepartmentManagementService.getDepartmentById(id);
	}

}
