package com.vietis.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vietis.task.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
	List<User> findByRoleid(Integer roleid);
	
}
