package com.dpoint.tutorial.dpointtestconsumer.controller;

import com.dpoint.tutorial.dpointtestconsumer.dto.Employee;
import com.dpoint.tutorial.dpointtestconsumer.service.EmployeeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final EmployeeApiService employeeApiService;

    @Autowired
    public ConsumerController(EmployeeApiService employeeApiService) {
        this.employeeApiService = employeeApiService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployeesFromApi() {
        return employeeApiService.getAllEmployees();
    }
}
