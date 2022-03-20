package com.paymentology.transactions.matcher.models;

import org.springframework.web.servlet.ModelAndView;

import com.paymentology.transactions.matcher.constants.Errors;
import com.paymentology.transactions.matcher.constants.Models;

public class TransactionsMatcherModels {
	
	public static ModelAndView matchTransactionsFromFile() {
		return new ModelAndView(Models.MATCH_TRANSACTIONS_FROM_FILES);
	}
	
	public static ModelAndView matchTransactionsFromFileGeneralError() {
		return matchTransactionsFromFile().addObject(Errors.ERROR, Errors.GENERAL_ERROR);
	}
	
	public static ModelAndView matchTransactionsFromFileErrorFileInProcess() {
		return matchTransactionsFromFile().addObject(Errors.ERROR, Errors.FILE_IN_PROCESS);
	}
}
