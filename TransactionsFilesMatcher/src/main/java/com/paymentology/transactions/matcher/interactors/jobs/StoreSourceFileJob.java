package com.paymentology.transactions.matcher.interactors.jobs;

import java.io.File;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymentology.transactions.matcher.constants.Models;
import com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.steps.StoreSourceFileStart;
import com.paymentology.transactions.matcher.models.TransactionsMatcherModels;
import com.paymentology.transactions.matcher.utils.Files;

@Service
public class StoreSourceFileJob {

	@Autowired private StoreSourceFileStart jobStarter;
	@Autowired private ProbableMatches probableMatches;
	
	public ModelAndView start(MultipartFile file1, MultipartFile file2, RedirectAttributes redir) {
		try {
			File source = Files.saveSourceFile(file1);
			File match = Files.saveMatchFile(file2);
			
			if(Objects.isNull(source) || Objects.isNull(match))
				return TransactionsMatcherModels.matchTransactionsFromFileErrorFileInProcess(redir);
				
			jobStarter.execute(source, match);
			redir.addFlashAttribute(Models.PROBABLE_MATCHES_PARAM, probableMatches.getAll());
			return TransactionsMatcherModels.matchTransactionsFromFileRedirect();
			}
		catch(Exception e) {
			e.printStackTrace();
			return TransactionsMatcherModels.matchTransactionsFromFileGeneralError(redir);
		}
	}
}
