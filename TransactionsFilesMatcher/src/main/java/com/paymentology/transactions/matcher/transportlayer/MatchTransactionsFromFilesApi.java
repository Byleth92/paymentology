package com.paymentology.transactions.matcher.transportlayer;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class MatchTransactionsFromFilesApi {

	@GetMapping("/matchFiles")
	public ModelAndView get() {
		
		return new ModelAndView("/matchTransactionsFromFiles");
	}
}
