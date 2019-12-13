package com.sbd.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbdInventoryApplication {

	public final static Logger logger = LoggerFactory.getLogger(SbdInventoryApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SbdInventoryApplication.class, args);
	}
}
