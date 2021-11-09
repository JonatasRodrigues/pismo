package br.com.pismo.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.pismo.model.Account;
import br.com.pismo.model.OperationType;
import br.com.pismo.model.Transaction;

public class TransactionDTO {

	@JsonProperty("account_id")
	private Long accountId;

	@JsonProperty("operation_type_id")
	private Long operationTypeId;

	private BigDecimal amount;
	
	@JsonProperty("transaction_id")
	private Long transactionId;

	public static Transaction converterToEntity(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setEventDate(Instant.now());

		Account account = new Account();
		account.setAccountId(transactionDTO.getAccountId());

		OperationType operationType = new OperationType();
		operationType.setOperationTypeId(transactionDTO.getOperationTypeId());

		transaction.setAccount(account);
		transaction.setOperationType(operationType);
		return transaction;
	}

	public static TransactionDTO converterToDTO(Transaction transaction) {
		TransactionDTO dto = new TransactionDTO();
		if (transaction != null) {
			dto.setTransactionId(transaction.getTransactionId());
			dto.setAccountId(transaction.getAccount().getAccountId());
			dto.setAmount(transaction.getAmount());
			dto.setOperationTypeId(transaction.getOperationType().getOperationTypeId());
		}

		return dto;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getOperationTypeId() {
		return operationTypeId;
	}

	public void setOperationTypeId(Long operationTypeId) {
		this.operationTypeId = operationTypeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "TransactionDTO [accountId=" + accountId + ", operationTypeId=" + operationTypeId + ", amount=" + amount
				+ "]";
	}

}
