package br.com.pismo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pismo.dto.TransactionDTO;
import br.com.pismo.exception.RequisicaoInvalidaException;
import br.com.pismo.model.Transaction;
import br.com.pismo.service.ITransactionService;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	private static final Logger logger = LogManager.getLogger(TransactionController.class);
	
	@Autowired
	private ITransactionService transactionService;
	
	@PostMapping("/transactions")
    public ResponseEntity<TransactionDTO> save(@RequestBody TransactionDTO transactionDTO) {
        logger.info("Saving new transaction : " + transactionDTO);      

        if(transactionDTO==null || transactionDTO.getAccountId() ==null 
        		|| transactionDTO.getAmount() == null || transactionDTO.getOperationTypeId() == null) {
        	
        	throw new RequisicaoInvalidaException("Invalid request.");
        }
        
        Transaction transaction = transactionService.save(TransactionDTO.converterToEntity(transactionDTO));
        return new ResponseEntity<TransactionDTO>(TransactionDTO.converterToDTO(transaction), HttpStatus.CREATED);
    }
}
