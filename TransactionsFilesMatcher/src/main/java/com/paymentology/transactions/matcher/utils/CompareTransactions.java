package com.paymentology.transactions.matcher.utils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;

public class CompareTransactions {
	
	private final static int PROBABLY_RELATED_EQUAL_VALUES_REFERENCE_NUMBER = 2;

	public static List<? extends Transaction> match(List<? extends Transaction> TransactionsFromComparingFile, List<TransactionSource> transactionsFromDatabase) {

		for(Transaction transaction : TransactionsFromComparingFile) {

			Optional<TransactionSource> equivalentTransactionFromDatabaseOpt = transactionsFromDatabase.stream().filter(t -> t.getTransactionId().equals(transaction.getTransactionId())).findFirst();
			
			if(equivalentTransactionFromDatabaseOpt.isPresent())
				transaction.setPerfectlyMatched(compareAllFields(transaction, equivalentTransactionFromDatabaseOpt.get()));
		}
		return TransactionsFromComparingFile;
	}
	
	private static boolean compareAllFields(Transaction transaction, TransactionSource equivalentTransactionFromDatabase) {
		
		if(CompareFieldTypes.areStringAndBigDecimalStrEqual(transaction.getTransactionAmount(), equivalentTransactionFromDatabase.getTransactionAmount())
		&& CompareFieldTypes.areTwoStringsEqual(transaction.getProfileName(), equivalentTransactionFromDatabase.getProfileName())
		&& CompareFieldTypes.areStringAndLocalDateTimeEqual(transaction.getTransactionDate(), equivalentTransactionFromDatabase.getTransactionDate())
		&& CompareFieldTypes.areTwoStringsEqual(transaction.getTransactionDescription(), equivalentTransactionFromDatabase.getTransactionDescription())
		&& CompareFieldTypes.areTwoStringsEqual(transaction.getTransactionNarrative(), equivalentTransactionFromDatabase.getTransactionNarrative())
		&& CompareFieldTypes.areTwoStringsEqual(transaction.getTransactionType(), equivalentTransactionFromDatabase.getTransactionType())
		&& CompareFieldTypes.areTwoStringsEqual(transaction.getWalletReference(), equivalentTransactionFromDatabase.getWalletReference()))
			return true;
		
		return false;
	}
	
	public static boolean isProbablyRelated(TransactionSource transactionNotMatched, TransactionSource transactionFromDatabase) {
		
			int identicalValuesCounter = 0;
		
			if(Objects.nonNull(transactionNotMatched.getTransactionAmount()) && transactionNotMatched.getTransactionAmount().equals(transactionFromDatabase.getTransactionAmount()))
				identicalValuesCounter++;
			
			if(Objects.nonNull(transactionNotMatched.getTransactionDate()) && transactionNotMatched.getTransactionDate().equals(transactionFromDatabase.getTransactionDate()))
				identicalValuesCounter++;
			
			if(Objects.nonNull(transactionNotMatched.getTransactionId()) && transactionNotMatched.getTransactionId().equals(transactionFromDatabase.getTransactionId()))
				identicalValuesCounter++;
			
			if(Objects.nonNull(transactionNotMatched.getWalletReference()) && transactionNotMatched.getWalletReference().equals(transactionFromDatabase.getWalletReference()))
				identicalValuesCounter++;
			
			return identicalValuesCounter >= PROBABLY_RELATED_EQUAL_VALUES_REFERENCE_NUMBER;
	}
}
