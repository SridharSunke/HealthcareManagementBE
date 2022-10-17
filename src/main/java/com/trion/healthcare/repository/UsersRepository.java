package com.trion.healthcare.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.trion.healthcare.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {

	Optional<Users> findByUserName(String userName);

	// Object findByfirstName(String firstname);

	// Optional<Users> findByFnameLname(String firstname, String lastname);

	// Users findByCriteria(String firstname,String lastName);

	@Query("select u from Users u where u.firstName=:FN and u.lastName=:LN")
	List<Users> findByfirstNameAndOrlastName(@Param("FN") String firstName, @Param("LN") String lastName);
	
	

}
//
//	@Query("SELECT u from Users u where u.firstName =:name  ")
//	List<Users> findByfirstName(@Param("name") String name);
//	
//	
//	@Query("Select u from Users u where u.firstName = :FN or u.lastName = :LN")
//	List<Users> findByfirstNameOrlastName(@Param("FN") String firstName, @Param("LN") String lastName);}
