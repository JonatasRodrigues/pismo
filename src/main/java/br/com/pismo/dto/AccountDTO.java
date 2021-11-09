package br.com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.pismo.model.Account;

public class AccountDTO {

	@JsonProperty("account_id")
	private Long accountId;

	@JsonProperty("document_number")
	private String documentNumber;

	public static Account converterToEntity(AccountDTO accountDTO) {
		Account account = new Account();
		account.setDocumentNumber(accountDTO.getDocumentNumber());
		return account;
	}

	public static AccountDTO converterToDTO(Account account) {
		AccountDTO dto = new AccountDTO();
		dto.setAccountId(account.getAccountId());
		dto.setDocumentNumber(account.getDocumentNumber());

		return dto;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	@Override
	public String toString() {
		return "AccountDTO [accountId=" + accountId + ", documentNumber=" + documentNumber + "]";
	}

}
