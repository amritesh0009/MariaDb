package com.demo.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.mysql.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
