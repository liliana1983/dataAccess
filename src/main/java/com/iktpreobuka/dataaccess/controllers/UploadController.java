package com.iktpreobuka.dataaccess.controllers;

import java.io.IOException;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.message.Message;
import com.iktpreobuka.dataaccess.message.Response;
import com.iktpreobuka.dataaccess.services.FileHandlerImpl;
import com.iktpreobuka.dataaccess.utils.ApacheCommonsCsvUtil;


@RestController
@RequestMapping(value="/dataaccess/")
public class UploadController {
	//private final SLF4JLogger logger = (SLF4JLogger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	public FileHandlerImpl fileHandler;
	
	Response response= new Response();
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "upload.html";
		}
	
	@RequestMapping(method = RequestMethod.GET, value = "uploadStatus")
	public String uploadStatus() {
	return "uploadStatus.html";
	}
/*	@RequestMapping(method=RequestMethod.POST,value="/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException{
		String result=null;
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		result=fileHandler.singleFileUpload(file,redirectAttributes);
	return result;
	}*/
	/*@GetMapping("/uploadsinglefile")
	public String index() {
		return "uploadsinglefile.html";
	}*/
	
	@PostMapping("/uploadsinglefile")
	public Response uploadSingleFile(@RequestParam("csvfile") MultipartFile csvfile, UserEntity user) {
		
		if(csvfile.getOriginalFilename().isEmpty()) {
			List<Message> message= new ArrayList<>();
			 message.add(new Message(csvfile.getOriginalFilename(),"There is no file to upload","fail"));
			response.setMessages(message);
			return response;
		}
		if(!ApacheCommonsCsvUtil.isCSVFile(csvfile)) {
	List<Message> message= new ArrayList<>();
	message.add(new Message(csvfile.getOriginalFilename(),"Not a CSV file","Fail"));
			response.setMessages(message);
			return response;
		}
		
		try {
			fileHandler.store(csvfile.getInputStream());
			List<Message> message= new ArrayList<>();
			message.add(new Message(csvfile.getOriginalFilename(),"message", "Successfully!"));
			response.setMessages(message);
		} catch (Exception e) {
			List<Message> message= new ArrayList<>();
			message.add(new Message(csvfile.getOriginalFilename(),"message", "Fail! "));
			response.setMessages(message);
		}			
		
		return response;		
	}


}
