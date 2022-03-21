package com.paymentology.transactions.matcher.models;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymentology.transactions.matcher.constants.Errors;
import com.paymentology.transactions.matcher.constants.Models;

public class TransactionsMatcherModels {
	
	public static ModelAndView matchTransactionsFromFile() {
		return new ModelAndView(Models.MATCH_TRANSACTIONS_FROM_FILES);
	}
	
	public static ModelAndView matchTransactionsFromFileRedirect() {
		return new ModelAndView(Models.MATCH_TRANSACTIONS_FROM_FILES_REDIRECT);
	}
	
	public static ModelAndView matchTransactionsFromFileGeneralError(RedirectAttributes redir) {
		redir.addFlashAttribute(Errors.ERROR, Errors.GENERAL_ERROR);
		return matchTransactionsFromFileRedirect();
	}
	
	public static ModelAndView matchTransactionsFromFileErrorFileInProcess(RedirectAttributes redir) {
		redir.addFlashAttribute(Errors.ERROR, Errors.FILE_IN_PROCESS);
		return matchTransactionsFromFileRedirect();
	}
	
	public static ModelAndView matchTransactionsFromFileErrorBothFilesNeeded(RedirectAttributes redir) {
		redir.addFlashAttribute(Errors.ERROR, Errors.UPLOAD_BOTH_FILES);
		return matchTransactionsFromFileRedirect();
	}
}
