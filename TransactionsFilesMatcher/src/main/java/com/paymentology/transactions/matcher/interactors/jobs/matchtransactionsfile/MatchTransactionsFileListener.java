package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile;

import java.io.File;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class MatchTransactionsFileListener implements JobExecutionListener {

	private File file2;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {}

	@Override
	public void afterJob(JobExecution jobExecution) {file2.delete();}
}
