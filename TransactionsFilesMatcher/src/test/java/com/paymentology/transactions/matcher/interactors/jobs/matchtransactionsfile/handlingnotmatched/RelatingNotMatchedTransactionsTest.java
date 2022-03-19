package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.handlingnotmatched;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentology.transactions.matcher.Commons;
import com.paymentology.transactions.matcher.domain.ProbablyMatchedTransactions;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.respositories.TransactionSourceRepository;

@ExtendWith(MockitoExtension.class)
class RelatingNotMatchedTransactionsTest extends Commons{

	@InjectMocks private RelatingNotMatchedTransactions relatingNotMatchedTransactions;
	@Mock private TransactionSourceRepository repository;
	
	@Test
	void comparingPossibleMatches() {
		
		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(transaction1);
		transactionList.add(transaction2);
		
		List<TransactionSource> transactionSourceList = new ArrayList<>();
		transactionSourceList.add(transactionSource1);
		
		when(repository.findByTransactionIdOrTransactionDate(Mockito.any(), Mockito.any())).thenReturn(transactionSourceList);
		
		
		ProbablyMatchedTransactions probableMatchedTransactions = relatingNotMatchedTransactions.comparingPossibleMatchesForNotMatchedTransactions(transactionList);
		
		assertEquals(probableMatchedTransactions.getNotMatchedTransactions().size(), 
					 probableMatchedTransactions.getProbableMatchedTransactions().size());
	}
}
