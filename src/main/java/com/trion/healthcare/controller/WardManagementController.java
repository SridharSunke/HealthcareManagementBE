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
import com.trion.healthcare.entity.WardManagement;
import com.trion.healthcare.exception.WradNotFoundException;
import com.trion.healthcare.service.impl.WardManagementServiceImpl;

@RestController
@RequestMapping("/wardM")
public class WardManagementController {
	@Autowired
	WardManagementServiceImpl wardManagementServiceImpl;

	@GetMapping("/all")
	public @ResponseBody List<WardManagement> getAll() {
		return wardManagementServiceImpl.findAll();

	}

//	public String save(WardManagement management) {
//
//		wardManagementServiceImpl.saveWard(management);
//
//		return "Suucessfully Saved";
//	}

	@GetMapping(path = "/getward")
	public @ResponseBody Map<WardManagement, List<Department>> getWardmanagemennt() {

		return wardManagementServiceImpl.getWardmanagemennt();

	}

	@PostMapping("/update")
	public String update(@RequestBody WardManagement management) {
		wardManagementServiceImpl.updateward(management);
		return "Successfully update your WardManagement";
	}

	@PutMapping("/add")
	public String add(@RequestBody WardManagement management) {
		wardManagementServiceImpl.saveWard(management);
		return "successfully Added your ward";
	}

	@DeleteMapping("/delete")
	public String delete(@RequestBody WardManagement management) {
		wardManagementServiceImpl.delete(management);
		return "Successfully deleted your ward";

	}

	@GetMapping("/getByWardId/{wardid}")
	public @ResponseBody WardManagement getByWardId(@PathVariable Integer wardid) throws WradNotFoundException {
		return wardManagementServiceImpl.getByWardId(wardid);

	}

	@GetMapping("/getWardDetail/{wardType}")
	public @ResponseBody WardManagement getWardDetail(@PathVariable String wardType) {
		return wardManagementServiceImpl.getWardDetail(wardType);
	}

	public WardManagementServiceImpl getWardManagementServiceImpl() {
		return wardManagementServiceImpl;
	}

	public void setWardManagementServiceImpl(WardManagementServiceImpl wardManagementServiceImpl) {
		this.wardManagementServiceImpl = wardManagementServiceImpl;
	}

	@GetMapping("/getname/{wardType}")
	public @ResponseBody List<String> getALL(@PathVariable String wardType) {
		return wardManagementServiceImpl.getByName(wardType);
	}

	@GetMapping("/getBycapacity/{capacity}")
	public @ResponseBody List<WardManagement> getByCapacity(@PathVariable Integer capacity) {

		return wardManagementServiceImpl.getByCapacity(capacity);
	}

	@GetMapping("/getByReporter/{reporter}")
	public @ResponseBody List<String> getByReporter(@PathVariable String reporter) {
		return wardManagementServiceImpl.getByReporter(reporter);
	}
}
