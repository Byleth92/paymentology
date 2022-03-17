package com.paymentology.transactions.matcher.datasources.jobs.matchtransactionsfile.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.datasources.database.TransactionSourceDao;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.utils.QueryBuilder;

@Component
public class MatchTransactionsWriter implements ItemWriter<Transaction>{

	@Autowired private TransactionSourceDao dao;
	
	@Override
	public void write(List<? extends Transaction> items) throws Exception {

		String query = QueryBuilder.buildQueryInLot(items);
		List<TransactionSource> teste = dao.selectInRange(query);
		
		System.out.println(teste);
	}
}
