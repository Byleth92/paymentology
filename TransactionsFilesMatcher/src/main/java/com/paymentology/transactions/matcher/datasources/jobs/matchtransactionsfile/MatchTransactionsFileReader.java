package com.paymentology.transactions.matcher.datasources.jobs.matchtransactionsfile;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.constants.BatchSettings;
import com.paymentology.transactions.matcher.datasources.jobs.CsvLineMapper;
import com.paymentology.transactions.matcher.domain.Transaction;

@Component
public class MatchTransactionsFileReader {

	@Autowired
	private CsvLineMapper<Transaction> lineMapper;

	@Bean
	@JobScope
	public FlatFileItemReader<Transaction> getReaderMatchTransaction() {
	  FlatFileItemReader<Transaction> reader = new FlatFileItemReader<Transaction>();
	  reader.setResource(new ClassPathResource(BatchSettings.MATCH_TRANSACTIONS_FILE));
	  reader.setLineMapper(lineMapper);
	  reader.setLinesToSkip(1);
	  return reader;
	}
}