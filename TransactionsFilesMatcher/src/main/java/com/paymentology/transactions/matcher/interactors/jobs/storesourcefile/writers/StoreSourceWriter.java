package com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.transactions.matcher.domain.Transaction;
import com.paymentology.transactions.matcher.entities.TransactionProcessingErrors;
import com.paymentology.transactions.matcher.enums.Jobs;
import com.paymentology.transactions.matcher.respositories.TransactionProcessingErrorsRepository;
import com.paymentology.transactions.matcher.respositories.TransactionSourceRepository;
import com.paymentology.transactions.matcher.utils.TransactionSourceConvert;

@Component
public class StoreSourceWriter implements ItemWriter<Transaction>{

	@Autowired private TransactionSourceRepository repository;
	@Autowired private TransactionProcessingErrorsRepository errorsRepository;
	
	/** Method responsible for storing all read transactions from the source file.*/
	@Override
	public void write(List<? extends Transaction> items) throws Exception {
		items.parallelStream().forEach(t -> {
			Transaction transaction = t;
			try {repository.save(TransactionSourceConvert.convert(transaction));}
			catch(Exception e) {saveError(transaction);};
		});
	}
	
	/** Method responsible for storing processing errors due to malformed lines in file.*/
	private void saveError(Transaction transaction) {
		 errorsRepository.save(TransactionProcessingErrors.builder()
		 .profileName(transaction.getProfileName())
		 .transactionDate(transaction.getTransactionDate())
		 .transactionAmount(transaction.getTransactionAmount())
		 .transactionNarrative(transaction.getTransactionNarrative())
		 .transactionDescription(transaction.getTransactionDescription())
		 .transactionId(transaction.getTransactionId())
		 .transactionType(transaction.getTransactionType())
		 .walletReference(transaction.getWalletReference())
		 .file(Jobs.SOURCE.name())
		 .build());
	}
}
