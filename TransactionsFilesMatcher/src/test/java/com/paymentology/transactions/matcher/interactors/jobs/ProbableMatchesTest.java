package com.paymentology.transactions.matcher.interactors.jobs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;

@ExtendWith(MockitoExtension.class)
class ProbableMatchesTest {

	@InjectMocks private ProbableMatches probableMatches;
	@Mock private ProbableMatchTransactionRepository repository;
	
	@Test
	void getAllProbableMatches() {probableMatches.getAll();}
}
