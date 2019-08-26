package com.PrestamosPrima.service;

import java.util.List;

import com.PrestamosPrima.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteUser(int theId);
	
	public User findByEmailAndPassword(String email, String password);
	
}
