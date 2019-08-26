package com.PrestamosPrima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PrestamosPrima.dao.UserRepository;
import com.PrestamosPrima.entity.User;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);
	
		User theUser = null;
		
		if(result.isPresent()) {
			theUser = result.get();
		}else {
			// we didn't find the 
			throw new RuntimeException("Did not find User with id - " + theId);
		}
		
		return theUser;
	}
	
	@Override
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);

	}

	@Override
	public void save(User theUser) {
		userRepository.save(theUser);

	}

	@Override
	public void deleteUser(int theId) {
		userRepository.deleteById(theId);

	}

}
