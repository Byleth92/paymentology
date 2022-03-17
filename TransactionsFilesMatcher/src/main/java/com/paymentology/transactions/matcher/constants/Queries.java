package com.paymentology.transactions.matcher.constants;

public interface Queries {
	public static final String SELECT_IN_TRANSACTION_SOURCE = "SELECT * FROM TRANSACTION_SOURCE WHERE ";
	public static final String TRANSACTION_SOURCE_EQUALS = "TRANSACTION_ID = '%s' ";
	public static final String OR_TRANSACTION_SOURCE_EQUALS = "OR TRANSACTION_ID = '%s' ";
	
}
