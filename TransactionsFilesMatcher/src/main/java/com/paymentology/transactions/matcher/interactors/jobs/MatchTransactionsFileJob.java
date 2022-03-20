package com.paymentology.transactions.matcher.interactors.jobs;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.steps.MatchTransactionsFileStart;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class MatchTransactionsFileJob {

	@Autowired private MatchTransactionsFileStart matchTransactionsFileStart;
	
	public void start(File file2) {
		try {matchTransactionsFileStart.execute(file2);} 
		catch (Exception e) {e.printStackTrace();}
	}
}
