package com.PrestamosPrima.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PrestamosPrima.entity.Account;
import com.PrestamosPrima.entity.Transaction;
import com.PrestamosPrima.entity.User;
import com.PrestamosPrima.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	
	private User user = new User();
	private AccountService accountService;
	
	@Autowired
	public AccountController(AccountService theAccountService) {
		accountService = theAccountService;
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listAccounts(Model theModel) {

		List<Account> theAccounts = accountService.findByUser(user);
		theModel.addAttribute("user", user);
		// add to the spring model
		theModel.addAttribute("accounts", theAccounts);
		
		
		return "accounts/list-accounts";
		
	}
	
	
	@GetMapping("/listByUser")
	public String listAccountsByUserId(Model theModel, @ModelAttribute("user") User theUser) {
		if(theUser == null) {
			theUser = user;
		}else {
			user = theUser;
		}

		List<Account> theAccounts = accountService.findByUser(theUser);
		
		// add to the spring model
		theModel.addAttribute("accounts", theAccounts);
		theModel.addAttribute("user", theUser);
		
		return "accounts/list-accounts";
		
	}
	
	// add mapping for "/list"
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Account theAccount = new Account();
		theAccount.setUser(user);
		// add to the spring model
		theModel.addAttribute("account", theAccount);
		
		return "accounts/accounts-form";
		
	}
	
	
	// add mapping for "/list"
	@PostMapping("/save")
	public String saveAccount(@ModelAttribute("account") Account theAccount) {
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(theAccount.getTotalAmount());
		newTransaction.setAccount(theAccount);
		newTransaction.setType("Open Account");
		newTransaction.setTransactionDate(new Date());
		theAccount.setUser(user);
		
		// save the  
		accountService.save(theAccount);
		accountService.save(newTransaction);
		// use a redirect to prevent duplicate submissions		
		return "redirect:/accounts/list";
		
	}
	
	
	
	// add mapping for "/showFormForUpdate
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("accountId") int theId, Model theModel) {
		
		// get the  from the service
		Account theAccount = accountService.findById(theId);
		theAccount.setUser(user);
		
		// set  as a model attribute to pre populate the form
		theModel.addAttribute("account", theAccount);
		
		
		//send over to out form
		return "accounts/accounts-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("accountId") int theId) {
		// delete the 
		accountService.deleteAccount(theId);
		
		// redirect to /s/list
		return "redirect:/accounts/list";
		
	}
	
	// add mapping for "/showFormForTransactions
	@GetMapping("/showFormForTransactionsList")
	public String showFormForTransactions(@RequestParam("accountId") int theId, Model theModel) {
		
		// get the  from the service
		Account theAccount = accountService.findById(theId);
		theAccount.setUser(user);
		// set  as a model attribute to pre populate the form
		theModel.addAttribute("account", theAccount);
		
		
		//send over to out form
		return "accounts/transactions-list";

	}
	
	
	// add mapping for "/showFormForTransactions
	@GetMapping("/showFormForTransactions")
	public String showFormForTransactions(@RequestParam("accountId") int theId, @RequestParam("deposit") boolean deposit, Model theModel) {
		
		// get the  from the service
		Account theAccount = accountService.findById(theId);
		theAccount.setUser(user);
		// set  as a model attribute to pre populate the form
		theModel.addAttribute("account", theAccount);
		theModel.addAttribute("deposit", deposit);
		
		
		//send over to out form
		return "accounts/deposit-withdraw";

	}

	
	// add mapping for "/list"
	@PostMapping("/saveDeposit")
	public String saveDeposit(@ModelAttribute("account") Account theAccount) {
		
		Transaction newTransaction = new Transaction();
		
		newTransaction.setAmount(theAccount.getModifierValue());
		newTransaction.setAccount(theAccount);
		newTransaction.setType("Deposit");
		newTransaction.setTransactionDate(new Date());
		theAccount.setTotalAmount(theAccount.getTotalAmount()+theAccount.getModifierValue());
		theAccount.setUser(user);
		
		// save the  
		accountService.save(theAccount);
		accountService.save(newTransaction);
		
		// use a redirect to prevent duplicate submissions		
		return "redirect:/accounts/list";
		
	}
	
	
	// add mapping for "/list"
	@PostMapping("/saveWithdrawal")
	public String saveWithdrawal(@ModelAttribute("account") Account theAccount) {
		
		Transaction newTransaction = new Transaction();
		
		newTransaction.setAmount(theAccount.getModifierValue());
		newTransaction.setAccount(theAccount);
		newTransaction.setType("Withdrawal");
		newTransaction.setTransactionDate(new Date());
		theAccount.setTotalAmount(theAccount.getTotalAmount()-theAccount.getModifierValue());
		theAccount.setUser(user);
		
		// save the  
		accountService.save(theAccount);
		accountService.save(newTransaction);
		
		// use a redirect to prevent duplicate submissions		
		return "redirect:/accounts/list";
		
	}
	
	
}




































