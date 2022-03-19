package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.handlingnotmatched.writers;

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
import com.paymentology.transactions.matcher.datasources.database.TransactionSourceDao;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.handlingnotmatched.RelatingNotMatchedTransactions;
import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.writers.MatchTransactionsWriter;
import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;

@ExtendWith(MockitoExtension.class)
class MatchTransactionsWriterTest extends Commons{

	@InjectMocks private MatchTransactionsWriter writer;
	@Mock private TransactionSourceDao dao;
	@Mock private RelatingNotMatchedTransactions relatingNotMatchedTransactions;
	@Mock private ProbableMatchTransactionRepository probableMatchTransactionRepository;
	
	@Test
	void matchTransactionWriter() throws Exception {
		
		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction1);
		
		List<TransactionSource> listTransactionSource = new ArrayList<>();
		listTransactionSource.add(transactionSource1);
		
		when(dao.selectInLot(Mockito.anyString())).thenReturn(listTransactionSource);
		when(relatingNotMatchedTransactions.comparingPossibleMatchesForNotMatchedTransactions(Mockito.any())).thenReturn(probablyMatchedTransactions);
		when(probableMatchTransactionRepository.save(Mockito.any())).thenReturn(probablyMatchedTransactions.getNotMatchedTransactions().get(0));
		writer.write(listTransaction);
	}
}
