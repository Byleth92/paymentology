package com.paymentology.transactions.matcher.interactors.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.steps.MatchTransactionsFileStart;

@Service
public class MatchTransactionsFileJob implements Job{

	@Autowired private MatchTransactionsFileStart matchTransactionsFileStart;
	
	@Override
	public void start() {
		try {Thread.sleep(700); matchTransactionsFileStart.execute();} 
		catch (Exception e) {e.printStackTrace();}
	}
}
