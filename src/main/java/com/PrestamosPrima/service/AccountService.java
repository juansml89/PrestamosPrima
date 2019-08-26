package com.PrestamosPrima.service;

import java.util.List;

import com.PrestamosPrima.entity.Account;
import com.PrestamosPrima.entity.Transaction;
import com.PrestamosPrima.entity.User;

public interface AccountService {
	
	public List<Account> findAll();
	
	public List<Account> findByUser(User theUser);
	
	public Account findById(int theId);
	
	public void save(Account theAccount);
	
	public void save(Transaction theTransaction);
	
	public void deleteAccount(int theId);
	
}
