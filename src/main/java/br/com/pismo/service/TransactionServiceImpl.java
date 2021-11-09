package br.com.pismo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pismo.enumerator.OperationTypeEnum;
import br.com.pismo.exception.DatabaseException;
import br.com.pismo.model.Transaction;
import br.com.pismo.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction save(Transaction transaction) {
		try {
			if(transaction.getOperationType().getOperationTypeId() != OperationTypeEnum.PAGAMENTO.getId()) {
				transaction.setAmount(transaction.getAmount().multiply(new BigDecimal(-1)));
			}
			
			return transactionRepository.save(transaction);
		} catch (Exception e) {
			throw new DatabaseException("Occured error to try save transaction: " + e.getMessage());
		}
		
	}

}
