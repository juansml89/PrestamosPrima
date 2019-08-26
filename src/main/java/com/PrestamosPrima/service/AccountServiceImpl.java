package com.PrestamosPrima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PrestamosPrima.dao.AccountRepository;
import com.PrestamosPrima.dao.TransactionRepository;
import com.PrestamosPrima.entity.Account;
import com.PrestamosPrima.entity.Transaction;
import com.PrestamosPrima.entity.User;

@Service
public class AccountServiceImpl implements AccountService {

	AccountRepository accountRepository;
	TransactionRepository transactionRepository;
	@Autowired
	public AccountServiceImpl(AccountRepository theAccountRepository, TransactionRepository  transactionRepository) {
		accountRepository = theAccountRepository;
		this.transactionRepository =transactionRepository;
	}
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	@Override
	public List<Account> findByUser(User theUser) {
		return accountRepository.findByUser(theUser);
	}

	@Override
	public Account findById(int theId) {
		Optional<Account> result = accountRepository.findById(theId);
	
		Account theAccount = null;
		
		if(result.isPresent()) {
			theAccount = result.get();
		}else {
			// we didn't find the 
			throw new RuntimeException("Did not find Account with id - " + theId);
		}
		
		return theAccount;
	}

	@Override
	public void save(Account theAccount) {
		accountRepository.save(theAccount);
	}
	
	@Override
	public void save(Transaction theTransaction) {
		transactionRepository.save(theTransaction);
	}

	@Override
	public void deleteAccount(int theId) {
		accountRepository.deleteById(theId);

	}

}
