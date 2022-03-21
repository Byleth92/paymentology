package com.paymentology.transactions.matcher.interactors.jobs.storesourcefile;

import java.io.File;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.interactors.jobs.MatchTransactionsFileJob;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class StoreSourceFileListener implements JobExecutionListener {
	
	@Autowired private MatchTransactionsFileJob matchTransactionsFileJob;
	private File file1;
	private File file2;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {}

	@Override
	public void afterJob(JobExecution jobExecution) {
		file1.delete();
		
		/** Initializing second job, responsible for matching the stored data from the source file and matching its information with the match file.*/
		matchTransactionsFileJob.start(file2);
	}
}
