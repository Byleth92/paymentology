package com.paymentology.transactions.matcher.interactors.jobs;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.steps.MatchTransactionsFileStart;

@ExtendWith(MockitoExtension.class)
class MatchTransactionsFileJobTest {

	@InjectMocks private MatchTransactionsFileJob matchTransactionsFileJob;
	@Mock private MatchTransactionsFileStart matchTransactionsFileStart;
	
	@Test
	void test() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		doNothing().when(matchTransactionsFileStart).execute();
		matchTransactionsFileJob.start();
	}
	
	@Test
	void test2() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		doThrow(JobParametersInvalidException.class).when(matchTransactionsFileStart).execute();
		matchTransactionsFileJob.start();
	}
}
