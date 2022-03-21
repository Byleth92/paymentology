package com.paymentology.transactions.matcher.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;

@Repository
public interface ProbableMatchTransactionRepository extends JpaRepository<ProbableMatchTransaction, Integer>{
	public List<ProbableMatchTransaction> findAllByOrderById();
	public List<ProbableMatchTransaction> findByTransactionId(String transactionId);
}
