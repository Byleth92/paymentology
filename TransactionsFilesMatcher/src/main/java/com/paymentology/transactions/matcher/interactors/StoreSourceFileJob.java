package com.paymentology.transactions.matcher.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.steps.StoreSourceFileStart;

@Service
public class StoreSourceFileJob implements Job{

	@Autowired private StoreSourceFileStart jobStarter;
	
	@Override
	public void start() {
		try {jobStarter.execute();}
		catch(Exception e) {e.printStackTrace();}
	}
}
