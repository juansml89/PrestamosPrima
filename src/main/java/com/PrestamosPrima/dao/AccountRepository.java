package com.PrestamosPrima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PrestamosPrima.entity.Account;
import com.PrestamosPrima.entity.User;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	 List<Account> findByUser(User theUser);
	
}
