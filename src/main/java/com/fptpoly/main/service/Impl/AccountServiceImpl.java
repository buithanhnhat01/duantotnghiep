package com.fptpoly.main.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptpoly.main.Dao.AccountRepository;
import com.fptpoly.main.Entity.Account;
import com.fptpoly.main.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Account> getAllAccount() {
		return 	accountRepository.findAll();
	}

	@Override
	public void saveAccount(Account account) {
		accountRepository.save(account);
		
	}

	@Override
	public Account getAccountById(String matv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(String matv) {
		// TODO Auto-generated method stub
		
	}


}
