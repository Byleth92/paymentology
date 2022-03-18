package com.paymentology.transactions.matcher.respositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentology.transactions.matcher.entities.TransactionSource;

@Repository
public interface TransactionSourceRepository extends JpaRepository<TransactionSource, Integer>{	
	public List<TransactionSource> findByTransactionIdOrTransactionDateOrTransactionAmount(String transactionId, LocalDateTime transactionDate, BigDecimal transactionAmount);
}
