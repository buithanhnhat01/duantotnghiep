package com.fptpoly.main.service;

import java.util.List;

import com.fptpoly.main.Entity.Account;

public interface AccountService {
	List<Account> getAllAccount();
	void saveAccount(Account account);
	Account getAccountById(String matv);
	void deleteAccount(String matv);

}
