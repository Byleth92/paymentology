package com.paymentology.transactions.matcher.utils;

import com.paymentology.transactions.matcher.domain.Transaction;

public class TransactionFactory {
	 public static Transaction create(String[] values) {
		 Transaction transaction = new Transaction();
		 transaction.setProfileName(values[0]);
		 transaction.setTransactionDate(values[1]);
		 transaction.setTransactionAmount(values[2]);
		 transaction.setTransactionNarrative(values[3]);
		 transaction.setTransactionDescription(values[4]);
		 transaction.setTransactionId(values[5]);
		 transaction.setTransactionType(values[6]);
		 transaction.setWalletReference(values[7]);
		 return transaction;
	 }
}
