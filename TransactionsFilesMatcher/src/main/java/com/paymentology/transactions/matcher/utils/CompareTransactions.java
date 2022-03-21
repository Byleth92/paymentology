package com.paymentology.transactions.matcher.utils;

import java.util.List;
import java.util.Optional;

import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionProcessingErrors;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.enums.Jobs;
import com.paymentology.transactions.matcher.respositories.TransactionProcessingErrorsRepository;

public class CompareTransactions {
	
	/** Method responsible for setting true to all perfect matches based on the comparison of transactions. */
	public static List<? extends Transaction> match(List<? extends Transaction> TransactionsFromComparingFile, List<TransactionSource> transactionsFromDatabase, TransactionProcessingErrorsRepository repositoryErrors) {

		for(Transaction transaction : TransactionsFromComparingFile) {
			Optional<TransactionSource> equivalentTransactionFromDatabaseOpt = transactionsFromDatabase.stream().filter(t -> t.getTransactionId().equals(transaction.getTransactionId())).findFirst();
			
			if(equivalentTransactionFromDatabaseOpt.isPresent())
				transaction.setPerfectlyMatched(compareAllFields(transaction, equivalentTransactionFromDatabaseOpt.get(), repositoryErrors));
		}
		return TransactionsFromComparingFile;
	}
	
	/** Method responsible for comparing every field from both files in order to check if it is a perfect match or not, and storing errors if there are any due to malformation from the match file data. */
	private static Boolean compareAllFields(Transaction transaction, TransactionSource equivalentTransactionFromDatabase, TransactionProcessingErrorsRepository repositoryErrors) {
		
		try {
			if(CompareFieldTypes.areStringAndBigDecimalStrEqual(transaction.getTransactionAmount(), equivalentTransactionFromDatabase.getTransactionAmount())
					&& CompareFieldTypes.areTwoStringsEqual(transaction.getProfileName(), equivalentTransactionFromDatabase.getProfileName())
					&& CompareFieldTypes.areStringAndLocalDateTimeEqual(transaction.getTransactionDate(), equivalentTransactionFromDatabase.getTransactionDate())
					&& CompareFieldTypes.areTwoStringsEqual(transaction.getTransactionDescription(), equivalentTransactionFromDatabase.getTransactionDescription())
					&& CompareFieldTypes.areTwoStringsEqual(transaction.getTransactionNarrative(), equivalentTransactionFromDatabase.getTransactionNarrative())
					&& CompareFieldTypes.areTwoStringsEqual(transaction.getTransactionType(), equivalentTransactionFromDatabase.getTransactionType())
					&& CompareFieldTypes.areTwoStringsEqual(transaction.getWalletReference(), equivalentTransactionFromDatabase.getWalletReference()))
						return true;
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
						return null;
		}
		
		return false;
	}
}
