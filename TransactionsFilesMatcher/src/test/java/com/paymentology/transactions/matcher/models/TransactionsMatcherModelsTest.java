package com.paymentology.transactions.matcher.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(MockitoExtension.class)
class TransactionsMatcherModelsTest {

	@InjectMocks private TransactionsMatcherModels transactionsMatcherModels;
	@Mock private RedirectAttributes redir;
	
	@Test
	void test() {
		TransactionsMatcherModels.matchTransactionsFromFile();
		TransactionsMatcherModels.matchTransactionsFromFileErrorFileInProcess(redir);
		TransactionsMatcherModels.matchTransactionsFromFileErrorBothFilesNeeded(redir);
	}
}
