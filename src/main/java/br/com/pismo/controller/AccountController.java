package br.com.pismo.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pismo.dto.AccountDTO;
import br.com.pismo.exception.RequisicaoInvalidaException;
import br.com.pismo.model.Account;
import br.com.pismo.service.IAccountService;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

	private static final Logger logger = LogManager.getLogger(AccountController.class);

	@Autowired
	private IAccountService accountService;

	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
		logger.info("Searching account by id: {}", accountId);
		
		Optional<Account>account = accountService.getAccountById(accountId);
		
		if(account.isPresent())
			return new ResponseEntity<AccountDTO>(AccountDTO.converterToDTO(account.get()),HttpStatus.OK);
		
		return new ResponseEntity<String>("Account not found.", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/accounts")
	public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO) {
		logger.info("Saving new account : " + accountDTO);

		if (accountDTO == null || accountDTO.getDocumentNumber() == null || "".equals(accountDTO.getDocumentNumber())) {
			throw new RequisicaoInvalidaException("Invalid request.");
		}

		Account account = accountService.save(AccountDTO.converterToEntity(accountDTO));
		return new ResponseEntity<AccountDTO>(AccountDTO.converterToDTO(account), HttpStatus.CREATED);
	}
}
