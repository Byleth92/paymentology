package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.handlingnotmatched;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.domain.ProbablyMatchedTransactions;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;
import com.paymentology.transactions.matcher.entities.TransactionProcessingErrors;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.enums.Jobs;
import com.paymentology.transactions.matcher.respositories.TransactionProcessingErrorsRepository;
import com.paymentology.transactions.matcher.respositories.TransactionSourceRepository;
import com.paymentology.transactions.matcher.utils.FindProbableMatches;
import com.paymentology.transactions.matcher.utils.TransactionSourceConvert;

@Component
public class RelatingNotMatchedTransactions {

	@Autowired private TransactionSourceRepository repository;
	@Autowired private TransactionProcessingErrorsRepository repositoryErrors;
	
	/** Method responsible for storing within two lists from a single object every probable match in index order. That is, index 0 of one list corresponds to a possible match from index 0 the other list. */
	public ProbablyMatchedTransactions comparingPossibleMatchesForNotMatchedTransactions(List<Transaction> notMatchedTransactions) {
		
		ProbablyMatchedTransactions probablyRelatedTransactions = new ProbablyMatchedTransactions();
		
		for(Transaction transaction: notMatchedTransactions) {
			try {
				
			TransactionSource convertedNotMatchTransaction = TransactionSourceConvert.convert(transaction);
			List<TransactionSource> candidates = repository.findByTransactionIdOrTransactionDate(convertedNotMatchTransaction.getTransactionId(), convertedNotMatchTransaction.getTransactionDate());
			candidates.stream().forEach(c -> {
				if(FindProbableMatches.isProbablyRelated(convertedNotMatchTransaction, c)) {
			
					String probableMatchBidirectionalId = UUID.randomUUID().toString();
					
					probablyRelatedTransactions.getNotMatchedTransactions()
											   .add(ProbableMatchTransaction
											   .builder()
											   .probableMatchBidirectionalId(probableMatchBidirectionalId)
											   .transactionSourceId(convertedNotMatchTransaction.getId())
											   .profileName(convertedNotMatchTransaction.getProfileName())
											   .transactionDate(convertedNotMatchTransaction.getTransactionDate())
											   .transactionAmount(convertedNotMatchTransaction.getTransactionAmount())
											   .transactionNarrative(convertedNotMatchTransaction.getTransactionNarrative())
											   .transactionDescription(convertedNotMatchTransaction.getTransactionDescription())
											   .transactionId(convertedNotMatchTransaction.getTransactionId())
											   .transactionType(convertedNotMatchTransaction.getTransactionType())
											   .walletReference(convertedNotMatchTransaction.getWalletReference())
											   .build());
					
					probablyRelatedTransactions.getProbableMatchedTransactions()
											   .add(ProbableMatchTransaction
											   .builder()
											   .probableMatchBidirectionalId(probableMatchBidirectionalId)
											   .transactionSourceId(c.getId())
											   .profileName(c.getProfileName())
											   .transactionDate(c.getTransactionDate())
											   .transactionAmount(c.getTransactionAmount())
											   .transactionNarrative(c.getTransactionNarrative())
											   .transactionDescription(c.getTransactionDescription())
											   .transactionId(c.getTransactionId())
											   .transactionType(c.getTransactionType())
											   .walletReference(c.getWalletReference())
											   .build());
					}
			});
		}
		catch(Exception e) {
			repositoryErrors.save(TransactionProcessingErrors.builder()
								 .profileName(transaction.getProfileName())
								 .transactionDate(transaction.getTransactionDate())
								 .transactionAmount(transaction.getTransactionAmount())
								 .transactionNarrative(transaction.getTransactionNarrative())
								 .transactionDescription(transaction.getTransactionDescription())
								 .transactionId(transaction.getTransactionId())
								 .transactionType(transaction.getTransactionType())
								 .walletReference(transaction.getWalletReference())
								 .file(Jobs.MATCH.name())
								 .build());
		}
	}
	return probablyRelatedTransactions;
	}
}
