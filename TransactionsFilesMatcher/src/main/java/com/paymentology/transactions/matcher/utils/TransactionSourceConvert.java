package com.paymentology.transactions.matcher.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.paymentology.transactions.matcher.constants.DatesAndTime;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;

public class TransactionSourceConvert {
	public static TransactionSource convert(Transaction transaction) {
		return	TransactionSource.builder()
								 .profileName(transaction.getProfileName())
								 .transactionAmount(new BigDecimal(transaction.getTransactionAmount()))
								 .transactionDate(LocalDateTime.parse(transaction.getTransactionDate(), DatesAndTime.FORMATTER_DATE_TIME_ddMMyyyy_HHmmss))
								 .transactionDescription(transaction.getTransactionDescription())
								 .transactionId(transaction.getTransactionId())
								 .transactionNarrative(transaction.getTransactionNarrative())
								 .transactionType(transaction.getTransactionType())
								 .walletReference(transaction.getWalletReference())
								 .build();
	}
}
