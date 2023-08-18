package com.dpoint.tutorial.dpointtestconsumer.service;

import com.dpoint.tutorial.dpointtestconsumer.dto.Employee;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Service
public class EmployeeApiService {

    private final RestTemplate restTemplate;
    private final String employeeApiUrl;

    public EmployeeApiService(@Value("${employee.api.url}") String employeeApiUrl) {
        this.restTemplate = new RestTemplate();
        this.employeeApiUrl = employeeApiUrl;
    }

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                employeeApiUrl + "/api/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        );
        return response.getBody();
    }
}
