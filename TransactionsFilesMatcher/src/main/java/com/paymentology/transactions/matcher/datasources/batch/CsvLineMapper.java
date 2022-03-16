package com.paymentology.transactions.matcher.datasources.batch;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.constants.BatchSettings;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.utils.TransactionFactory;

@Component
public class CsvLineMapper<T> implements LineMapper<Transaction> {
	
	 @Override
	 public Transaction mapLine(String line, int lineNumber) throws Exception {
		 DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		 delimitedLineTokenizer.setDelimiter(BatchSettings.COMMA);
		 return TransactionFactory.create(delimitedLineTokenizer.tokenize(line).getValues());
	 	}
	}
