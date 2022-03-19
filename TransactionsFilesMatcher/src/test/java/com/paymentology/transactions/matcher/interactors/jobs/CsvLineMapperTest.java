package com.paymentology.transactions.matcher.interactors.jobs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentology.transactions.matcher.domain.Transaction;

@ExtendWith(MockitoExtension.class)
class CsvLineMapperTest {

	@InjectMocks private CsvLineMapper<Transaction> csvLineMapper;
	private String line = "Card Campaign,2014-01-11 22:27:44,-20000,*MOLEPSATM25MOLEPOLOLEBW,DEDUCT,0584011808649511,1,P_NzI2ODY2ODlfMTM4MjcwMTU2NS45MzA5";
	
	@Test
	void mapLinner() throws Exception {
		
		Transaction transaction = csvLineMapper.mapLine(line, 1);
		
		assertEquals(transaction.getProfileName(), "Card Campaign");
		assertEquals(transaction.getTransactionAmount(), "-20000");
		assertEquals(transaction.getTransactionDate(), "2014-01-11 22:27:44");
		assertEquals(transaction.getTransactionDescription(), "DEDUCT");
		assertEquals(transaction.getTransactionId(), "0584011808649511");
		assertEquals(transaction.getTransactionNarrative(), "*MOLEPSATM25MOLEPOLOLEBW");
		assertEquals(transaction.getTransactionType(), "1");
		assertEquals(transaction.getWalletReference(), "P_NzI2ODY2ODlfMTM4MjcwMTU2NS45MzA5");
	}
}
