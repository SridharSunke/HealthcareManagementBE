package com.trion.healthcare.repository;

import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.trion.healthcare.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

	

}
