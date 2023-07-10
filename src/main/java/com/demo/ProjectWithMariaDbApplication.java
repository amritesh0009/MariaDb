package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectWithMariaDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectWithMariaDbApplication.class, args);
		System.out.println("working on the project");
	}
}
