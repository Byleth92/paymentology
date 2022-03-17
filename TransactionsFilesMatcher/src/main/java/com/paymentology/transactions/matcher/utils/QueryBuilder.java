package com.paymentology.transactions.matcher.utils;

import java.util.List;

import com.paymentology.transactions.matcher.constants.Queries;
import com.paymentology.transactions.matcher.domain.Transaction;

public class QueryBuilder {
	public static String buildQueryInLot(List<? extends Transaction> transactions) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(Queries.SELECT_IN_TRANSACTION_SOURCE);
		sb.append(String.format(Queries.TRANSACTION_SOURCE_EQUALS, transactions.get(0).getTransactionId()));
		for(int i=1; i < transactions.size(); i++) {
			sb.append(String.format(Queries.OR_TRANSACTION_SOURCE_EQUALS, transactions.get(i).getTransactionId()));
		}
		return sb.toString();
	}
}
