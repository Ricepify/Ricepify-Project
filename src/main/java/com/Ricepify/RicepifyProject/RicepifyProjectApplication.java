package com.Ricepify.RicepifyProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.Ricepify.RicepifyProject.Repositories")
public class RicepifyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RicepifyProjectApplication.class, args);
		System.out.println("test");
	}

}
