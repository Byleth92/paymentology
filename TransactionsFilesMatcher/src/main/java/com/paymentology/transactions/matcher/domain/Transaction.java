package com.paymentology.transactions.matcher.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	private String profileName;;
	private String transactionDate;
	private String TransactionAmount;
	private String TransactionNarrative;
	private String TransactionDescription;
	private String TransactionId;
	private String TransactionType;
	private String WalletReference;
	private boolean perfectlyMatched;
}
