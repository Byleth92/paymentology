package com.paymentology.transactions.matcher.domain;

import java.util.ArrayList;
import java.util.List;

import com.paymentology.transactions.matcher.entities.TransactionSource;

import lombok.Getter;

@Getter
public class ProbablyRelatedTransactions {
	private List<TransactionSource> notMatchedTransactions = new ArrayList<>();
	private List<TransactionSource> probablyRelatedTransactions = new ArrayList<>();
}
