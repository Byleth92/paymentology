package com.paymentology.transactions.matcher.datasources.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class Listener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Job completed.");
	}

}
