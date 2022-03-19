package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.writers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.datasources.database.TransactionSourceDao;
import com.paymentology.transactions.matcher.domain.ProbablyMatchedTransactions;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.handlingnotmatched.RelatingNotMatchedTransactions;
import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;
import com.paymentology.transactions.matcher.utils.CompareTransactions;
import com.paymentology.transactions.matcher.utils.QueryBuilder;

@Component
public class MatchTransactionsWriter implements ItemWriter<Transaction>{

	@Autowired private TransactionSourceDao dao;
	@Autowired private RelatingNotMatchedTransactions relatingNotMatchedTransactions;
	@Autowired private ProbableMatchTransactionRepository probableMatchTransactionRepository;
	
	@Override
	public void write(List<? extends Transaction> transactionsFromComparingFile) throws Exception {

		String query = QueryBuilder.buildQueryInLot(transactionsFromComparingFile);
		List<TransactionSource> transactionsFromDatabase = dao.selectInLot(query);
		
		transactionsFromComparingFile = CompareTransactions.match(transactionsFromComparingFile, transactionsFromDatabase);
		List<Transaction> notPrefectlyMatchedTransactions = transactionsFromComparingFile.stream().filter(t -> !t.isPerfectlyMatched()).collect(Collectors.toList());

	
		ProbablyMatchedTransactions probablyRelatedTransactions = relatingNotMatchedTransactions.comparingNotRelatedTransactions(notPrefectlyMatchedTransactions);
		saveProbableMatches(probablyRelatedTransactions);
	}
	
	private void saveProbableMatches(ProbablyMatchedTransactions probablyRelatedTransactions) {
		for(int i=0; i < probablyRelatedTransactions.getNotMatchedTransactions().size(); i++) {
			probableMatchTransactionRepository.save(probablyRelatedTransactions.getNotMatchedTransactions().get(i));
			probableMatchTransactionRepository.save(probablyRelatedTransactions.getProbableMatchedTransactions().get(i));
		}
	}
}
