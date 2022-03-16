package com.paymentology.transactions.matcher.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentology.transactions.matcher.entities.TransactionSource;

public interface TransactionSourceRepository extends JpaRepository<TransactionSource, Integer>{}
