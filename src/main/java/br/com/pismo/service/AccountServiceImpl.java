package br.com.pismo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pismo.exception.DatabaseException;
import br.com.pismo.model.Account;
import br.com.pismo.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	public Account save(Account account) {
		try {
			return accountRepository.save(account);
		} catch (Exception e) {
			throw new DatabaseException("Occured error to try save account: " + e.getMessage());
		}
	}

	public Optional<Account> getAccountById(Long id) {
		return accountRepository.findByAccountId(id);
	}

}
