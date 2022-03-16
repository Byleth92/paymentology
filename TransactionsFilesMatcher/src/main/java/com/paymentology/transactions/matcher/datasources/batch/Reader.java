package com.paymentology.transactions.matcher.datasources.batch;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.constants.BatchSettings;
import com.paymentology.transactions.matcher.domain.Transaction;

@Component
public class Reader {
	
	@Autowired
	private CsvLineMapper<Transaction> lineMapper;

	@Bean
	@JobScope
	public FlatFileItemReader<Transaction> getReader() {
	FlatFileItemReader<Transaction> reader = new FlatFileItemReader<Transaction>();
	  reader.setResource(new ClassPathResource(BatchSettings.SOURCE_FILE));
	  reader.setLineMapper(lineMapper);
	  reader.setLinesToSkip(1);
	  return reader;
	}
}
