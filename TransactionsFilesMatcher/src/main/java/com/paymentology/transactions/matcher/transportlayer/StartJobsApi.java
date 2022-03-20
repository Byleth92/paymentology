package com.paymentology.transactions.matcher.transportlayer;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.paymentology.transactions.matcher.interactors.jobs.StoreSourceFileJob;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class StartJobsApi {

	@Autowired private StoreSourceFileJob storeSourceFileJob;
	
	@PostMapping("/match-transactions")
	public ModelAndView get(MultipartFile file1, MultipartFile file2) {
		
		if(Objects.isNull(file1) || 
				   Objects.isNull(file2) || 
				   Objects.isNull(file1.getOriginalFilename()) || 
				   Objects.isNull(file2.getOriginalFilename())) {
			return null;
		}
		
		try {storeSourceFileJob.start(file1, file2);} 
		catch (Exception e) {e.printStackTrace();};		
		
		return null;
	}
}
