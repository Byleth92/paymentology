package com.paymentology.transactions.matcher.interactors.jobs.storesourcefile;

import java.io.File;
import java.net.MalformedURLException;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.interactors.jobs.CsvLineMapper;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class StoreSourceFileReader {
	
	@Autowired private CsvLineMapper<Transaction> lineMapper;
	private File file1;
	
	@Bean
	@JobScope
	public FlatFileItemReader<Transaction> getReaderStoreSource() throws MalformedURLException {
	  FlatFileItemReader<Transaction> reader = new FlatFileItemReader<Transaction>();
	  reader.setResource(new UrlResource(file1.toURI()));
	  reader.setLineMapper(lineMapper);
	  reader.setLinesToSkip(1);
	  return reader;
	}
}
