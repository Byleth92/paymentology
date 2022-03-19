package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.interactors.jobs.Flag;

@Component
public class MatchTransactionsFileListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {}

	@Override
	public void afterJob(JobExecution jobExecution) {
		Flag.isJobRunning = false;
	}
}
