package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.ResourceNotFoundException;
import com.demo.mysql.entity.Employee;
import com.demo.mysql.repo.EmployeeRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class EmployeeController {

	private EmployeeRepo employeeRepo;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		System.out.println("hello");
		return employeeRepo.findAll();
	}
	
	@GetMapping("/employees/{id}")
	
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Integer employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Integer employeeId, @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Integer employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepo.delete(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
