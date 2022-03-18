package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.handlingnotmatched;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.domain.ProbablyRelatedTransactions;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.respositories.TransactionSourceRepository;
import com.paymentology.transactions.matcher.utils.CompareTransactions;

@Component
public class RelatingNotMatchedTransactions {

	@Autowired private TransactionSourceRepository repository;
	
	public ProbablyRelatedTransactions comparingNotRelatedTransactions(List<Transaction> notMatchedTransactions) {
		
		ProbablyRelatedTransactions probablyRelatedTransactions = new ProbablyRelatedTransactions();
		
		for(Transaction transaction: notMatchedTransactions) {
			List<TransactionSource> candidates = repository.findByTransactionIdOrTransactionDateOrTransactionAmount(transaction.getTransactionId(), transaction.getTransactionDate(), transaction.getTransactionAmount());
			candidates.stream().forEach(c -> {
				if(CompareTransactions.isProbablyRelated(transaction, c)) {
					probablyRelatedTransactions.getNotMatchedTransactions().add(transaction);
					probablyRelatedTransactions.getProbablyRelatedTransactions().add(c);
				}
			});
		}
		
		return probablyRelatedTransactions;
	}
}
