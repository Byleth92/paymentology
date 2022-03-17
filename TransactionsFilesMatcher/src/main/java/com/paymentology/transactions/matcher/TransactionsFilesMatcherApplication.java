package com.paymentology.transactions.matcher;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paymentology.transactions.matcher.interactors.StoreSourceFileJob;

@SpringBootApplication
@EnableBatchProcessing
public class TransactionsFilesMatcherApplication implements CommandLineRunner{

	@Autowired private StoreSourceFileJob storeSourceFileJob;
	
	public static void main(String[] args) {
		SpringApplication.run(TransactionsFilesMatcherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storeSourceFileJob.start();
	}
}
