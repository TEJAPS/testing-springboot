package com.dpoint.tutorial.dpointtestproducer.service;


import com.dpoint.tutorial.dpointtestproducer.entity.Employee;
import com.dpoint.tutorial.dpointtestproducer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
