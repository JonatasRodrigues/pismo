package br.com.pismo.service;

import java.util.Optional;

import br.com.pismo.model.Account;

public interface IAccountService {

	Account save(Account account);
	Optional<Account> getAccountById(Long id);
}
