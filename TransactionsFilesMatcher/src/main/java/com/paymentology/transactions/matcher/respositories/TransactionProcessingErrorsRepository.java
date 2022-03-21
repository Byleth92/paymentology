package com.paymentology.transactions.matcher.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentology.transactions.matcher.entities.TransactionProcessingErrors;

public interface TransactionProcessingErrorsRepository extends JpaRepository<TransactionProcessingErrors, Integer>{}
