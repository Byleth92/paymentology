package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.steps;

import java.util.UUID;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.constants.StepNames;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.MatchTransactionsFileListener;
import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.MatchTransactionsFileReader;
import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.writers.MatchTransactionsWriter;

@Component
public class MatchTransactionsFileStart {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	@Autowired private MatchTransactionsFileReader reader;
	@Autowired private MatchTransactionsFileListener listener;
	@Autowired private MatchTransactionsWriter writer;
	@Autowired private JobLauncher jobLauncher;
	
	  public void execute() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		  SimpleJobBuilder jobBuilder = jobBuilderFactory.get(UUID.randomUUID().toString())
				     .listener(listener)
				     .start(stepBuilderFactory.get(StepNames.MATCHING_TRANSACTIONS_FILE_JOB_STEP_START)
				     .<Object, Transaction>chunk(50)
				     .reader(reader.getReaderMatchTransaction())
				     .writer(writer)
				     .chunk(50).build());
		  
		  jobLauncher.run(jobBuilder.build(), new JobParameters());
	  }	  
}
