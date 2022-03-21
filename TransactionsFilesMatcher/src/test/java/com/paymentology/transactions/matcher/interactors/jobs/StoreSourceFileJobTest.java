package com.paymentology.transactions.matcher.interactors.jobs;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.steps.StoreSourceFileStart;

@ExtendWith(MockitoExtension.class)
class StoreSourceFileJobTest {

	@InjectMocks private StoreSourceFileJob storeSourceFileJob;
	@Mock private StoreSourceFileStart jobStarter;
	@Mock private MultipartFile file1;
	@Mock private MultipartFile file2;
	@Mock private RedirectAttributes redir;
	@Mock private ProbableMatches probableMatches;
	
	@Test
	void storeSourceFileJobWithNoErrors() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, IllegalStateException, IOException {
		doNothing().when(jobStarter).execute(Mockito.any(), Mockito.any());
		storeSourceFileJob.start(file1, file2, redir);
	}
	
	@Test
	void storeSourceFileJobWithException() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, MalformedURLException {
		doThrow(JobParametersInvalidException.class).when(jobStarter).execute(Mockito.any(), Mockito.any());
		storeSourceFileJob.start(file1, file2, redir);
	}
}
