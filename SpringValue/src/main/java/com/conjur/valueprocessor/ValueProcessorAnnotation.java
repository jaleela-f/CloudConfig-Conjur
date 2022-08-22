package com.conjur.valueprocessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValueProcessorAnnotation implements CommandLineRunner {
	
	@Value("${dbUserName}")
	private String  name;
	
	@Value("${dbPassword}")
	private String  password;
	
	
	public static void main(String[] args)
	{
		SpringApplication.run(ValueProcessorAnnotation.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Value from SpringApplicaiton>>>>>"+name+"Password"+password);
		
	}

}
