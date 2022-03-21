package com.paymentology.transactions.matcher.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.paymentology.transactions.matcher.constants.BatchSettings;

public class Files {
	
	public static File saveSourceFile(MultipartFile file1) throws IllegalStateException, IOException {
		File file = new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.SOURCE_FILE_FOLDER + file1.getOriginalFilename());
		
		if(file.exists())
			return null;
		
		file1.transferTo(new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.SOURCE_FILE_FOLDER + file1.getOriginalFilename()));
		return file;
	}
	
	public static File saveMatchFile(MultipartFile file2) throws IllegalStateException, IOException {
		File file = new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.MATCH_FILE_FOLDER + file2.getOriginalFilename());
		
		if(file.exists())
			return null;
		
		file2.transferTo(new File(BatchSettings.RESOURCES_FOLDER + BatchSettings.MATCH_FILE_FOLDER + file2.getOriginalFilename()));
		return file;
	}

}
