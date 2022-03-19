package com.paymentology.transactions.matcher.transportlayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.paymentology.transactions.matcher.entities.ProbableMatchTransaction;
import com.paymentology.transactions.matcher.interactors.jobs.ProbableMatches;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ProbableMatchesApi {

	@Autowired private ProbableMatches probableMatches;
	
	@GetMapping("/probableMatches")
	public ModelAndView get() {
		List<ProbableMatchTransaction> allProbableMatches = probableMatches.getAll();	
		return null;
	}
}
