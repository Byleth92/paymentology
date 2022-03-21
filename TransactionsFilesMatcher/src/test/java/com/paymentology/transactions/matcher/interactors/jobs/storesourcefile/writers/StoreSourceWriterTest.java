package com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.writers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentology.transactions.matcher.Commons;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.respositories.TransactionSourceRepository;

@ExtendWith(MockitoExtension.class)
class StoreSourceWriterTest extends Commons{

	@InjectMocks private StoreSourceWriter writer;
	@Mock private TransactionSourceRepository repository;
	
	@Test
	void storeSourceWriter() throws Exception {
		
		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(transaction1);
		transactionList.add(transaction2);
		
		when(repository.save(Mockito.any())).thenReturn(transactionSource1);
		writer.write(transactionList);
	}
}
