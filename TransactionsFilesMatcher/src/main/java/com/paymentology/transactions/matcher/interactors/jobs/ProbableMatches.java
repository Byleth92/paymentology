package com.paymentology.transactions.matcher.interactors.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;
import com.paymentology.transactions.matcher.entities.ProbablyNotFoundMatch;
import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;
import com.paymentology.transactions.matcher.respositories.ProbablyNotFoundMatchRepository;

@Service
public class ProbableMatches {
	
	@Autowired private ProbableMatchTransactionRepository probableMatchTransactionRepository;
	@Autowired private ProbablyNotFoundMatchRepository probablyNotFoundMatchRepository;
	
	public List<ProbableMatchTransaction> getAll(){	
		return probableMatchTransactionRepository.findAllByOrderById();
	}
	
	public List<ProbablyNotFoundMatch> getAllProbablyNotFound(){	
		return probablyNotFoundMatchRepository.findAll();
	}
}
