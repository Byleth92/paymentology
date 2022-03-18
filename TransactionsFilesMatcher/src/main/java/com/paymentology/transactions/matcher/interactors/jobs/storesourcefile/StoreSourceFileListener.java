package com.paymentology.transactions.matcher.interactors.jobs.storesourcefile;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.interactors.MatchTransactionsFileJob;

@Component
public class StoreSourceFileListener implements JobExecutionListener {
	
	@Autowired private MatchTransactionsFileJob matchTransactionsFileJob;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {}

	@Override
	public void afterJob(JobExecution jobExecution) {
		new Thread(() -> {matchTransactionsFileJob.start();}).start();
		System.out.println("Job completed.");
	}
}
