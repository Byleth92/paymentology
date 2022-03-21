package com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.respositories.TransactionSourceRepository;
import com.paymentology.transactions.matcher.utils.TransactionSourceConvert;

@Component
public class StoreSourceWriter implements ItemWriter<Transaction>{

	@Autowired private TransactionSourceRepository repository;
	
	@Override
	public void write(List<? extends Transaction> items) throws Exception {
		items.parallelStream().forEach(t -> {repository.save(TransactionSourceConvert.convert(t));});
	}
}
