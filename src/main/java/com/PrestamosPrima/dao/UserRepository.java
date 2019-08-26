package com.PrestamosPrima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PrestamosPrima.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// that's it
	

    User findByEmailAndPassword(String email, String password);
 


	
	// add a method to sort by last name
	public List<User> findAllByOrderByLastNameAsc();
}
