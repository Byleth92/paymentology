package com.paymentology.transactions.matcher.transportlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.paymentology.transactions.matcher.interactors.jobs.Flag;
import com.paymentology.transactions.matcher.interactors.jobs.StoreSourceFileJob;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class StartJobsApi {

	@Autowired private StoreSourceFileJob storeSourceFileJob;
	
	@GetMapping("/matchTransactions")
	public ModelAndView get() {
		
		if(Flag.isJobRunning)
			return null;
		
		new Thread(() -> {
			try {storeSourceFileJob.start();} 
			catch (Exception e) {e.printStackTrace();}}).start();		
		
		return null;
	}
}
