package com.demo.service.impl;

import org.springframework.stereotype.Service;

import com.demo.mysql.repo.EmployeeRepo;
import com.demo.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
public class EmployeeServiceImpol implements EmployeeService{

	private EmployeeRepo employeeRepo;
	
}
