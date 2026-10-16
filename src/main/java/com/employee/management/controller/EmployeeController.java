package com.employee.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.entity.Employee;
import com.employee.management.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('READ_EMPLOYEE')")
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }
    
    
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_EMPLOYEE')")
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);
    }
    
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        Employee existingEmployee =
                employeeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());

        return employeeRepository.save(existingEmployee);
    }
    
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    public String deleteEmployee(@PathVariable Long id) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);

        return "Employee deleted successfully";
    }
}