package com.paymentology.transactions.matcher;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.assertj.core.util.Arrays;

import com.paymentology.transactions.matcher.constants.DatesAndTime;
import com.paymentology.transactions.matcher.domain.ProbablyMatchedTransactions;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.utils.TransactionFactory;

public class Commons {

	protected Transaction transaction1;
	protected Transaction transaction2;
	protected TransactionSource transactionSource1;
	protected ProbablyMatchedTransactions probablyMatchedTransactions;
	
	public Commons() {
		transactionSource1 = TransactionSource.builder()
				 .profileName("profileName")
				 .transactionAmount(BigDecimal.TEN)
				 .transactionDate(LocalDateTime.parse("2021-10-02 10:00:00", DatesAndTime.FORMATTER_DATE_TIME_ddMMyyyy_HHmmss))
				 .transactionDescription("transactionDescription")
				 .transactionId("transactionId")
				 .transactionNarrative("transactionNarrative")
				 .transactionType("transactionType")
				 .walletReference("walletReference")
				 .build();
		
		String[] valuesTest = Arrays.array("profileName", "2021-10-02 10:00:00", "10", "transactionAmount", "transactionNarrative", "transactionDescription", "transactionId", "transactionType", "walletReference");
		transaction1 = TransactionFactory.create(valuesTest);
		
		valuesTest = Arrays.array("profileName2", "2021-10-02 10:00:00", "1430.00", "transactionAmount2", "transactionNarrative2", "transactionDescription2", "transactionId2", "transactionType2", "walletReference2");
		transaction2 = TransactionFactory.create(valuesTest);
		
		probablyMatchedTransactions = new ProbablyMatchedTransactions();
		
		ProbableMatchTransaction probableMatchTransaction = ProbableMatchTransaction.builder()
																					.id(1)
																					.profileName("profileName")
																					.probableMatchBidirectionalId("probableMatchBidirectionalId")
																					.transactionDate(LocalDateTime.now())
																					.transactionAmount(BigDecimal.TEN)
																					.transactionNarrative("transactionNarrative")
																					.transactionDescription("transactionDescription")
																					.transactionId("transactionId")
																					.transactionSourceId(1)
																					.transactionType("transactionType")
																					.walletReference("walletReference")
																					.build();
		
		probablyMatchedTransactions.getNotMatchedTransactions().add(probableMatchTransaction);
		probablyMatchedTransactions.getProbableMatchedTransactions().add(probableMatchTransaction);
	}
	
}
