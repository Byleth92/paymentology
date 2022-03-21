package com.paymentology.transactions.matcher.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	private String profileName;;
	private String transactionDate;
	private String transactionAmount;
	private String transactionNarrative;
	private String transactionDescription;
	private String transactionId;
	private String transactionType;
	private String walletReference;
	private Boolean perfectlyMatched;
}
