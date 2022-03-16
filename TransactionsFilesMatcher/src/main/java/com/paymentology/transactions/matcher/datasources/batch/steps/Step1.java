package com.paymentology.transactions.matcher.datasources.batch.steps;

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

import com.paymentology.transactions.matcher.datasources.batch.Listener;
import com.paymentology.transactions.matcher.datasources.batch.Reader;
import com.paymentology.transactions.matcher.datasources.batch.writers.Writer;
import com.paymentology.transactions.matcher.domain.Transaction;

@Component
public class Step1 {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	@Autowired private Reader reader;
	@Autowired private Listener listener;
	@Autowired private Writer writer;
	@Autowired private Step2 step2;
	
	  public void execute() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		  SimpleJobBuilder jobBuilder = jobBuilderFactory.get(UUID.randomUUID().toString())
				     .listener(listener)
				     .start(stepBuilderFactory.get("Step1")
				     .<Object, Transaction>chunk(50)
				     .reader(reader.getReader())
				     .writer(writer)
				     .chunk(50).build());
		  
		  step2.execute(jobBuilder);
	  }	  
}
