package com.paymentology.transactions.matcher.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentology.transactions.matcher.Commons;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;

@ExtendWith(MockitoExtension.class)
class CompareTransactionsTest extends Commons{

	@InjectMocks private CompareTransactions compareTransactions;
	
	@Test
	void test() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction1);
		transactions.add(transaction2);
		
		List<TransactionSource> transactionsFromFile = new ArrayList<>();
		transactionsFromFile.add(transactionSource1);
		
		CompareTransactions.match(transactions, transactionsFromFile);
	}
}
