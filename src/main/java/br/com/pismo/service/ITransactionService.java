package br.com.pismo.service;

import org.springframework.stereotype.Service;

import br.com.pismo.model.Transaction;

@Service
public interface ITransactionService {

	Transaction save(Transaction transaction);
}
