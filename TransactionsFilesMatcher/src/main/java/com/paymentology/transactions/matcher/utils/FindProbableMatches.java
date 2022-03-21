package com.paymentology.transactions.matcher.utils;

import java.util.Objects;

import com.paymentology.transactions.matcher.entities.TransactionSource;

public class FindProbableMatches {
	
	private final static int PROBABLY_RELATED_EQUAL_VALUES_REFERENCE_NUMBER = 2;
	
	/** Method responsible for comparing data from both files and finding if there are enough coincidences to consider it a probable match. */
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
