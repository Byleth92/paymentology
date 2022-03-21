package com.paymentology.transactions.matcher.datasources.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.paymentology.transactions.matcher.Commons;
import com.paymentology.transactions.matcher.constants.Queries;
import com.paymentology.transactions.matcher.entities.TransactionSource;

@ExtendWith(MockitoExtension.class)
public class TransactionSourceDaoTest extends Commons{

	@InjectMocks private TransactionSourceDao dao;
	@Mock private NamedParameterJdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	@Test
	void selectingInLot() {
		
		List<TransactionSource> list = new ArrayList<>();
		list.add(transactionSource1);
		
		when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.any(RowMapper.class))).thenReturn(list);
		List<TransactionSource> returnedList = dao.selectInLot(Queries.SELECT_IN_TRANSACTION_SOURCE);
		
		assertEquals(list.size(), returnedList.size());
		assertEquals(list.get(0).getProfileName(), returnedList.get(0).getProfileName());
		assertEquals(list.get(0).getTransactionDate(), returnedList.get(0).getTransactionDate());
	}
}
