package com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.writers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.constants.DatesAndTime;
import com.paymentology.transactions.matcher.datasources.database.TransactionSourceDao;
import com.paymentology.transactions.matcher.domain.ProbablyMatchedTransactions;
import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.ProbablyNotFoundMatch;
import com.paymentology.transactions.matcher.entities.TransactionSource;
import com.paymentology.transactions.matcher.interactors.jobs.matchtransactionsfile.handlingnotmatched.RelatingNotMatchedTransactions;
import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;
import com.paymentology.transactions.matcher.respositories.ProbablyNotFoundMatchRepository;
import com.paymentology.transactions.matcher.respositories.TransactionProcessingErrorsRepository;
import com.paymentology.transactions.matcher.utils.CompareTransactions;
import com.paymentology.transactions.matcher.utils.QueryBuilder;

@Component
public class MatchTransactionsWriter implements ItemWriter<Transaction>{

	@Autowired private TransactionSourceDao dao;
	@Autowired private RelatingNotMatchedTransactions relatingNotMatchedTransactions;
	@Autowired private ProbableMatchTransactionRepository probableMatchTransactionRepository;
	@Autowired private ProbablyNotFoundMatchRepository probablyNotFoundMatchRepository;
	@Autowired private TransactionProcessingErrorsRepository transactionProcessinErrorsRepository;
	
	@Override
	public void write(List<? extends Transaction> transactionsFromComparingFile) throws Exception {

		String query = QueryBuilder.buildQueryInLot(transactionsFromComparingFile);
		
		/** Selecting data in lot in order of not requesting to many times from database*/
		List<TransactionSource> transactionsFromDatabase = dao.selectInLot(query);
		
		/** Comparing data from both Files and saving transaction errors when they appear in the match file */
		transactionsFromComparingFile = CompareTransactions.match(transactionsFromComparingFile, transactionsFromDatabase, transactionProcessinErrorsRepository);
		
		/** Filtering every transaction that was not perfectly matched */
		List<Transaction> notPrefectlyMatchedTransactions = transactionsFromComparingFile.stream().filter(t -> Objects.nonNull(t.getPerfectlyMatched()) && !t.getPerfectlyMatched()).collect(Collectors.toList());
	
		/** Relating which transactions from the source file could be a possible match with transactions from the match file. */
		ProbablyMatchedTransactions probablyRelatedTransactions = relatingNotMatchedTransactions.comparingPossibleMatchesForNotMatchedTransactions(notPrefectlyMatchedTransactions);
		
		/** Storing probable relations between transactions. */
		saveProbableMatches(probablyRelatedTransactions);
		
		/** Storing transactions which possible matches were not found. */
		saveProbablyNotFoundTransaction(transactionsFromComparingFile, transactionsFromDatabase);
	}
	
	private void saveProbableMatches(ProbablyMatchedTransactions probablyRelatedTransactions) {
		for(int i=0; i < probablyRelatedTransactions.getNotMatchedTransactions().size(); i++) {
			probableMatchTransactionRepository.save(probablyRelatedTransactions.getNotMatchedTransactions().get(i));
			probableMatchTransactionRepository.save(probablyRelatedTransactions.getProbableMatchedTransactions().get(i));
		}
	}
	
	private void saveProbablyNotFoundTransaction(List<? extends Transaction> transactionsFromComparingFile, List<TransactionSource> transactionsFromDatabase) {
		for(int i=0; i < transactionsFromComparingFile.size(); i++) {
			final String transactionId = transactionsFromComparingFile.get(i).getTransactionId();
			if(!transactionsFromDatabase.stream().filter(t -> t.getTransactionId().equals(transactionId)).findFirst().isPresent()) {
				saveProbablyNotFound(transactionsFromComparingFile.get(i));
			}
		}
	}
	
	private void saveProbablyNotFound(Transaction p) {	
		if(probableMatchTransactionRepository.findByTransactionId(p.getTransactionId()).size() == 0) {
			
			ProbablyNotFoundMatch pnm = ProbablyNotFoundMatch.builder()
															 .profileName(p.getProfileName())
															 .transactionDate(LocalDateTime.parse(p.getTransactionDate(), DatesAndTime.FORMATTER_DATE_TIME_ddMMyyyy_HHmmss))
															 .transactionAmount(new BigDecimal(p.getTransactionAmount()))
															 .transactionNarrative(p.getTransactionNarrative())
															 .transactionDescription(p.getTransactionDescription())
															 .transactionId(p.getTransactionId())
															 .transactionType(p.getTransactionType())
															 .walletReference(p.getWalletReference())
															 .build();
			probablyNotFoundMatchRepository.save(pnm);
		}
	}
}
