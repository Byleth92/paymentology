package com.paymentology.transactions.matcher.transportlayer;

import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UploadFiles {
	
	
	@PostMapping("/upload-csv-files")
    public String uploadCSVFile1(MultipartFile file1, MultipartFile file2) {
		
		if(Objects.isNull(file1) || 
		   Objects.isNull(file2) || 
		   Objects.isNull(file1.getOriginalFilename()) || 
		   Objects.isNull(file2.getOriginalFilename())) {
			//Erro
		}
		return null;
	}
}
