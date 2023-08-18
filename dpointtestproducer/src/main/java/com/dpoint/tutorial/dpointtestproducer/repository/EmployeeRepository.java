package com.dpoint.tutorial.dpointtestproducer.repository;

import com.dpoint.tutorial.dpointtestproducer.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
