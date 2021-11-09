package br.com.pismo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pismo.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

	@SuppressWarnings("unchecked")
	public Account save(Account account);
	public Optional<Account> findByAccountId(Long dia);
}
