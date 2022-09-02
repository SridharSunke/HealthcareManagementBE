package com.trion.healthcare.repository;

import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.trion.healthcare.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {

}
