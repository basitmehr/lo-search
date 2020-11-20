package com.lo.search.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lo.search.service.GeoCityService;

@Component
public class CityRunner implements Runner {
	
	@Autowired
	GeoCityService geoCityService;

	@Override
	public void start() {
		try {
			geoCityService.handleRequest();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.exit(-1);
		
	}

}
