package com.br.myproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CrudApiApplication {
	
    @RequestMapping("/welcome")
    public String home() {
        return "welcome to the jungle";
    }
	
	private static final Logger log = LoggerFactory.getLogger(CrudApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrudApiApplication.class, args);
	}


}
