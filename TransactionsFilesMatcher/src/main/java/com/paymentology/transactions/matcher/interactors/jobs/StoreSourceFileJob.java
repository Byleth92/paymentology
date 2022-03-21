package com.paymentology.transactions.matcher.interactors.jobs;

import java.io.File;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymentology.transactions.matcher.constants.Models;
import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;
import com.paymentology.transactions.matcher.entities.ProbablyNotFoundMatch;
import com.paymentology.transactions.matcher.entities.TransactionProcessingErrors;
import com.paymentology.transactions.matcher.enums.Jobs;
import com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.steps.StoreSourceFileStart;
import com.paymentology.transactions.matcher.models.TransactionsMatcherModels;
import com.paymentology.transactions.matcher.respositories.ProbableMatchTransactionRepository;
import com.paymentology.transactions.matcher.respositories.ProbablyNotFoundMatchRepository;
import com.paymentology.transactions.matcher.respositories.TransactionProcessingErrorsRepository;
import com.paymentology.transactions.matcher.respositories.TransactionSourceRepository;
import com.paymentology.transactions.matcher.utils.Files;

@Service
public class StoreSourceFileJob {

	@Autowired private StoreSourceFileStart jobStarter;
	@Autowired private ProbableMatches probableMatchesService;
	@Autowired private TransactionSourceRepository repository;
	@Autowired private TransactionProcessingErrorsRepository errorsRepository;
	@Autowired private ProbableMatchTransactionRepository probableMatchRepository;
	@Autowired private ProbablyNotFoundMatchRepository probablyNotFoundMatchRepository;
	
	/** Method responsible for checking the validity of the data request and returning correspondent data.*/
	public ModelAndView start(MultipartFile file1, MultipartFile file2, RedirectAttributes redir) {
		try {
			File source = Files.saveSourceFile(file1);
			File match = Files.saveMatchFile(file2);
			
			if(Objects.isNull(source) || Objects.isNull(match))
				return TransactionsMatcherModels.matchTransactionsFromFileErrorFileInProcess(redir);
			
			deletePreviousData();
			jobStarter.execute(source, match);
			
			List<ProbableMatchTransaction> probableMatches = probableMatchesService.getAll();
			List<ProbablyNotFoundMatch> matchNotFound = probableMatchesService.getAllProbablyNotFound();
			List<TransactionProcessingErrors> processingErrors = errorsRepository.findAll();
			
			long possibleMatchesNumber = probableMatches.size() / 2;
			long perfectlyMatchedNumber = repository.count() - processingErrors.stream().filter(e -> Jobs.SOURCE.equals(Jobs.valueOf(e.getFile()))).count() - matchNotFound.size() - possibleMatchesNumber;
			perfectlyMatchedNumber = perfectlyMatchedNumber < 0 ? 0L : perfectlyMatchedNumber; 
			
			redir.addFlashAttribute(Models.PERFECTLY_MATCHED_NUMBER_PARAM, perfectlyMatchedNumber);
			redir.addFlashAttribute(Models.POSSIBLE_MATCHES_NUMBER_PARAM, possibleMatchesNumber);
			redir.addFlashAttribute(Models.NOT_FOUND_MATCHES_NUMBER_PARAM, matchNotFound.size());
			redir.addFlashAttribute(Models.PROCESSING_ERRORS_NUMBER, processingErrors.size());
			redir.addFlashAttribute(Models.PROBABLE_MATCHES_PARAM, probableMatches);
			redir.addFlashAttribute(Models.NOT_FOUND_MATCHES_PARAM, matchNotFound);
			redir.addFlashAttribute(Models.PROCESSING_ERRORS_TRANSACTION_PARAM, processingErrors);
			return TransactionsMatcherModels.matchTransactionsFromFileRedirect();
			}
		catch(Exception e) {
			e.printStackTrace();
			return TransactionsMatcherModels.matchTransactionsFromFileGeneralError(redir);
		}
	}
	
	/** Method responsible deleting any data regarding previously file match requests.*/
	private void deletePreviousData() {
		repository.deleteAll();
		errorsRepository.deleteAll();
		probableMatchRepository.deleteAll();
		probablyNotFoundMatchRepository.deleteAll();
	}
}
