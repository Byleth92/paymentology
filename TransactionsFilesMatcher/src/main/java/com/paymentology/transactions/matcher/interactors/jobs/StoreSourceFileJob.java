package com.paymentology.transactions.matcher.interactors.jobs;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.paymentology.transactions.matcher.constants.BatchSettings;
import com.paymentology.transactions.matcher.interactors.jobs.storesourcefile.steps.StoreSourceFileStart;

@Service
public class StoreSourceFileJob {

	@Autowired private StoreSourceFileStart jobStarter;
	
	public void start(MultipartFile file1, MultipartFile file2) {
		try {
		File source = saveSourceFile(file1);
		File match = saveMatchFile(file2);
		jobStarter.execute(source, match);}
		catch(Exception e) {e.printStackTrace();}
	}
	
	private File saveSourceFile(MultipartFile file1) throws IllegalStateException, IOException {
		File file = new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.SOURCE_FILE_FOLDER + file1.getOriginalFilename());
		file1.transferTo(new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.SOURCE_FILE_FOLDER + file1.getOriginalFilename()));
		return file;
	}
	
	private File saveMatchFile(MultipartFile file2) throws IllegalStateException, IOException {
		File file = new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.MATCH_FILE_FOLDER + file2.getOriginalFilename());
		file2.transferTo(new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.MATCH_FILE_FOLDER + file2.getOriginalFilename()));
		return file;
	}
}
