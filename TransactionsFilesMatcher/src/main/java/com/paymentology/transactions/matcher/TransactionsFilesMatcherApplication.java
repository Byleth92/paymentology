package com.paymentology.transactions.matcher;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class TransactionsFilesMatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsFilesMatcherApplication.class, args);
	}
}
