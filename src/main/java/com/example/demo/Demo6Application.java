package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

}
