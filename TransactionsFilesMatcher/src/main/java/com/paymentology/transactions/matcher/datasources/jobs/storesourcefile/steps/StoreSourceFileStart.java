package com.paymentology.transactions.matcher.datasources.jobs.storesourcefile.steps;

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
import com.paymentology.transactions.matcher.datasources.jobs.storesourcefile.StoreSourceFileReader;
import com.paymentology.transactions.matcher.datasources.jobs.storesourcefile.StoreSourceFileListener;
import com.paymentology.transactions.matcher.datasources.jobs.storesourcefile.writers.StoreSourceWriter;
import com.paymentology.transactions.matcher.domain.Transaction;

@Component
public class StoreSourceFileStart {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	@Autowired private StoreSourceFileReader reader;
	@Autowired private StoreSourceFileListener listener;
	@Autowired private StoreSourceWriter writer;
	@Autowired private JobLauncher jobLauncher;
	
	  public void execute() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		  SimpleJobBuilder jobBuilder = jobBuilderFactory.get(UUID.randomUUID().toString())
				     .listener(listener)
				     .start(stepBuilderFactory.get(StepNames.STORE_SOURCE_FILE_JOB_STEP_START)
				     .<Object, Transaction>chunk(50)
				     .reader(reader.getReaderStoreSource())
				     .writer(writer)
				     .chunk(50).build());
		  
		  jobLauncher.run(jobBuilder.build(), new JobParameters());
	  }	  
}
