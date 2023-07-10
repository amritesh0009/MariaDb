package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.postgresql.entity.Student;
import com.demo.postgresql.repo.StudentRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
public class StudentController {

	private StudentRepo studentRepo;
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student student){
		return studentRepo.save(student);
	}
	
	@GetMapping("/students")
	public List<Student> getStudent(){
		return studentRepo.findAll();
	}
}
