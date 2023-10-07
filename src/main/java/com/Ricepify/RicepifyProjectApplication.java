package com.Ricepify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RicepifyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RicepifyProjectApplication.class, args);
//		System.out.println("test");
	}

}
