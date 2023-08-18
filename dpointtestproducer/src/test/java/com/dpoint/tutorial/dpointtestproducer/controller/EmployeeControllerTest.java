package com.dpoint.tutorial.dpointtestproducer.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dpoint.tutorial.dpointtestproducer.entity.Employee;
import com.dpoint.tutorial.dpointtestproducer.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() throws Exception {
        // Arrange
        List<Employee> mockEmployeeList = Arrays.asList(
                new Employee("John", "Doe", "john@example.com"),
                new Employee("Jane", "Smith", "jane@example.com")
        );
        when(employeeService.getAllEmployees()).thenReturn(mockEmployeeList);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"));

        verify(employeeService, times(1)).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }
}
