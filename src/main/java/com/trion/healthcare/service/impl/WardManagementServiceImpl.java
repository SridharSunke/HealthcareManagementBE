package com.trion.healthcare.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trion.healthcare.entity.Department;
import com.trion.healthcare.entity.WardManagement;
import com.trion.healthcare.exception.WradNotFoundException;
import com.trion.healthcare.repository.DepartmentRepository;
import com.trion.healthcare.repository.WardManagementRepository;
import com.trion.healthcare.service.WardManagementService;

@Service
public class WardManagementServiceImpl implements WardManagementService {

	@Autowired
	WardManagementRepository managementRepository;
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public List<WardManagement> findAll() {
		return (List<WardManagement>) managementRepository.findAll();
	}
	
	public WardManagement getWardDetail(String wardType) {
		
		return findAll().stream().filter(ward ->ward.getWarddType().equals(wardType)).findFirst().get();
	}

	@Override
	public void saveWard(WardManagement management) {
		managementRepository.save(management);

	}

	@Override
	public void updateward(WardManagement management) {

		managementRepository.save(management);

	}

	@Override
	public void delete(WardManagement management) {
		managementRepository.delete(management);
	}

	@Override
	public WardManagement getByWardId(Integer wardid) throws WradNotFoundException {
		WardManagement management = null;
		if (managementRepository.findById(wardid).isPresent()) {
			management = managementRepository.findById(wardid).get();

		} else {
			throw new WradNotFoundException("Ward Not Found With this Id: " + wardid);
		}

		return management;
	}

	@Override
	public Map<WardManagement, List<Department>> getWardmanagemennt() {
		Map<WardManagement, List<Department>> rm = new HashMap<>();
//
//		WardManagement wrd= new WardManagement();
//		wrd.hashCode();
//		System.out.println(wrd);
//		
		departmentRepository.findAll().forEach(depart -> {
			if (rm.get(depart.getWardId()) == null) {
				List<Department> departlist = new ArrayList<Department>();
				departlist.add(depart);
				rm.put(depart.getWardId(), departlist);
			} else {
				List<Department> departlist = rm.get(depart.getWardId());
				departlist.add(depart);
			}

		});
		return rm;
	}

	@Override
	public List<WardManagement> getByCapacity(Integer capacity) {

		List<WardManagement> list = findAll().stream().filter(wards -> wards.getCapacity() == capacity).collect(Collectors.toList());
		
		return list;
	}

	@Override
	public List<String> getByName(String wardType) {
		List<String> names = findAll().stream().map(wards -> wards.getWarddType()).collect(Collectors.toList());
		
		return names;
	}

}
