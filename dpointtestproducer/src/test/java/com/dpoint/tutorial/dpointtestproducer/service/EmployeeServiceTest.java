package com.dpoint.tutorial.dpointtestproducer.service;


import com.dpoint.tutorial.dpointtestproducer.entity.Employee;
import com.dpoint.tutorial.dpointtestproducer.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize @Mock annotated fields
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        Employee employee1 = new Employee("John", "Doe", "john@example.com");
        Employee employee2 = new Employee("Jane", "Smith", "jane@example.com");
        List<Employee> mockEmployees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // Act
        List<Employee> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(mockEmployees, result);
        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }
}

