package com.paymentology.transactions.matcher.constants;

public interface Queries {
	public static final String SELECT_IN_TRANSACTION_SOURCE = "SELECT * FROM TRANSACTION_SOURCE WHERE ";
	public static final String WHERE_IN_TRANSACTION_SOURCE = "TRANSACTION_AMOUNT = :transactionAmount OR TRANSACTION_DATE = :transactionDate OR TRANSACTION_DESCRIPTION  = :transactionDescription OR TRANSACTION_ID = :transactionId";
}
