package com.trion.healthcare.service;

import java.util.List;
import java.util.Map;

import com.trion.healthcare.entity.Department;
import com.trion.healthcare.entity.Users;
import com.trion.healthcare.entity.WardManagement;
import com.trion.healthcare.exception.WradNotFoundException;

public interface WardManagementService {
	public List<WardManagement> findAll();

	void saveWard(WardManagement management);

	void updateward(WardManagement management);

	void delete(WardManagement management);

	WardManagement getByWardId(Integer wardid) throws WradNotFoundException;

	public Map<WardManagement, List<Department>> getWardmanagemennt();

	List<WardManagement> getByCapacity(Integer capacity);

	List<String> getByName(String wardType);

	List<String> getByReporter(String reporter);

}
