package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@SpringBootApplication
@Controller
public class Demo6Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo6Application.class, args);
	}

	@RequestMapping("input")
	public String home()
	{
		return "input.html";
	}
	
	@RequestMapping("listObjectsInput")
	public String listObjectsOfBucket()
	{
		return "listObjInp.html";
	}

@RequestMapping("getObject")
public ModelAndView getObject(@RequestParam("endpoint") String endpoint,@RequestParam("bucketName") String bucketName,@RequestParam("imageName") String imageName)
{
	
	ModelAndView mv=new ModelAndView();
			
	String finalpath=endpoint+"/"+bucketName+"/"+imageName;
	
	System.out.println(finalpath);
	mv.addObject("path",finalpath);
	mv.setViewName("getObject.html");
	return mv;
}

@RequestMapping("listObjects")
@ResponseBody
public List<String> listObjects(@RequestParam("endpoint") String endpoint,@RequestParam("bucketName") String bucketName,@RequestParam("accessKey") String accessKey,@RequestParam("secretKey") String secretKey)
{
	
	List<String> objList=new ArrayList<String>();
	BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey); // declare a new set of basic credentials that includes the Access Key ID and the Secret Access Key
	AmazonS3 cos = new AmazonS3Client(credentials); // create a constructor for the client by using the declared credentials.
	cos.setEndpoint(endpoint); // set the desired endpoint
	ObjectListing listing = cos.listObjects(bucketName); // get the list of objects in the 'sample' bucket
	List<S3ObjectSummary> summaries = listing.getObjectSummaries(); // create a list of object summaries

	for (S3ObjectSummary obj : summaries){ // for each object...
	  System.out.println("found:"+obj.getKey()); // display 'found: ' and then the name of the object
	  objList.add(obj.getKey());
	}
	return objList;
	//SampleInp
	//"https://s3.us-east.cloud-object-storage.appdomain.cloud"
	//final String accessKey = "372b1a02b29047aa999d72c44f6c6be0";
	//final String secretKey = "db09af915e2822066d05ea0120de275eb3234a41bbb80b6b";
}

}
