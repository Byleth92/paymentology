package com.paymentology.transactions.matcher.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentology.transactions.matcher.entities.ProbablyNotFoundMatch;

@Repository
public interface ProbablyNotFoundMatchRepository extends JpaRepository<ProbablyNotFoundMatch, Integer>{
}
