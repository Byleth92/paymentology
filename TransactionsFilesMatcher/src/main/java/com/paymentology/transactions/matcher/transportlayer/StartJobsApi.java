package com.paymentology.transactions.matcher.transportlayer;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymentology.transactions.matcher.constants.Strings;
import com.paymentology.transactions.matcher.interactors.jobs.StoreSourceFileJob;
import com.paymentology.transactions.matcher.models.TransactionsMatcherModels;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class StartJobsApi {

	@Autowired private StoreSourceFileJob storeSourceFileJob;
	
	/** Api Responsible for receiving the requested files to start processing the jobs.*/
	@PostMapping("/matchFiles")
	public ModelAndView get(MultipartFile file1, MultipartFile file2, RedirectAttributes redir) {
		
			if((Objects.isNull(file1) || Objects.isNull(file2)) || 
			   (file1.getOriginalFilename().isEmpty() || file2.getOriginalFilename().isEmpty()) ||
			   (!file1.getOriginalFilename().toUpperCase().endsWith(Strings.CSV_EXTENSION) || !file2.getOriginalFilename().toUpperCase().endsWith(Strings.CSV_EXTENSION)))
			return TransactionsMatcherModels.matchTransactionsFromFileErrorBothFilesNeeded(redir);
		
		return storeSourceFileJob.start(file1, file2, redir);
	}
}
