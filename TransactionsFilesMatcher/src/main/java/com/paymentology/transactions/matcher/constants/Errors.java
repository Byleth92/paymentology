package com.paymentology.transactions.matcher.constants;

public interface Errors {

	public static final String ERROR = "error";
	public static final String GENERAL_ERROR = "Sorry, we couldn't process your request at the moment. Try again later or contact the administrator.";
	public static final String FILE_IN_PROCESS = "There is already a file with the same name as at least one of the files you've uploaded being processed.<br>Change your file names and retry, or try again later.";
	public static final String UPLOAD_BOTH_FILES = "You need to upload both files for matching their transactions and both of them need to be csv files.";
}
