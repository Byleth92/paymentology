package com.paymentology.transactions.matcher.interactors.jobs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;
import com.paymentology.transactions.matcher.respositories.ProbablyNotFoundMatchRepository;

@ExtendWith(MockitoExtension.class)
class ProbableMatchesTest {

	@InjectMocks private ProbableMatches probableMatches;
	@Mock private ProbableMatchTransactionRepository repository;
	@Mock private ProbablyNotFoundMatchRepository probablyNotFoundMatchRepository;
	
	@Test
	void getAllProbableMatches() {probableMatches.getAll();}
	
	@Test
	void getAllProbablyNotFound() {probableMatches.getAllProbablyNotFound();}
}
