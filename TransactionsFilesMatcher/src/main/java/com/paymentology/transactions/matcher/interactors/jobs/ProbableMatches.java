package com.paymentology.transactions.matcher.interactors.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;
import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;

@Service
public class ProbableMatches {
	
	@Autowired private ProbableMatchTransactionRepository repository;
	
	public List<ProbableMatchTransaction> getAll(){	
		return repository.findAllByOrderById();
	}
}
