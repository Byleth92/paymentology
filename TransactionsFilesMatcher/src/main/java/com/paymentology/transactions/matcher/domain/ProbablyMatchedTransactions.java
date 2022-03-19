package com.paymentology.transactions.matcher.domain;

import java.util.ArrayList;
import java.util.List;

import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProbablyMatchedTransactions {
	private List<ProbableMatchTransaction> notMatchedTransactions = new ArrayList<>();
	private List<ProbableMatchTransaction> probableMatchedTransactions = new ArrayList<>();
}
