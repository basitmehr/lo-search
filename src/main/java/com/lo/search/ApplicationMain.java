package com.lo.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lo.search.runner.CityRunner;

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {
	
	
	@Autowired
	CityRunner cityRunner;

	@Override
	public void run(String... args) throws Exception {
		
		cityRunner.start();
	}
	
	public static void main(String[] args) {		
		SpringApplication.run(ApplicationMain.class, args);		
	}
}
