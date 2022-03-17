package com.paymentology.transactions.matcher.utils;

import java.util.List;
import java.util.Optional;

import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;

public class CompareTransactions {

	public static List<? extends Transaction> match(List<? extends Transaction> TransactionsFromComparingFile, List<TransactionSource> transactionsFromDatabase) {

		for(Transaction transaction : TransactionsFromComparingFile) {

			Optional<TransactionSource> equivalentTransactionFromDatabaseOpt = transactionsFromDatabase.stream().filter(t -> t.getTransactionId().equals(transaction.getTransactionId())).findFirst();
			
			if(equivalentTransactionFromDatabaseOpt.isPresent())
				transaction.setPerfectlyMatched(compareAllFields(transaction, equivalentTransactionFromDatabaseOpt.get()));
		}
		return TransactionsFromComparingFile;
	}
	
	private static boolean compareAllFields(Transaction transaction, TransactionSource equivalentTransactionFromDatabase) {
		
		if(CompareFiledTypes.areStringAndBigDecimalStrEqual(transaction.getTransactionAmount(), equivalentTransactionFromDatabase.getTransactionAmount())
		&& CompareFiledTypes.areTwoStringsEqual(transaction.getProfileName(), equivalentTransactionFromDatabase.getProfileName())
		&& CompareFiledTypes.areStringAndLocalDateTimeEqual(transaction.getTransactionDate(), equivalentTransactionFromDatabase.getTransactionDate())
		&& CompareFiledTypes.areTwoStringsEqual(transaction.getTransactionDescription(), equivalentTransactionFromDatabase.getTransactionDescription())
		&& CompareFiledTypes.areTwoStringsEqual(transaction.getTransactionNarrative(), equivalentTransactionFromDatabase.getTransactionNarrative())
		&& CompareFiledTypes.areTwoStringsEqual(transaction.getTransactionType(), equivalentTransactionFromDatabase.getTransactionType())
		&& CompareFiledTypes.areTwoStringsEqual(transaction.getWalletReference(), equivalentTransactionFromDatabase.getWalletReference()))
			return true;
		
		return false;
	}
}
