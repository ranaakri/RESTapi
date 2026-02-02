package com.example.rest_api_assignment.repository;

import com.example.rest_api_assignment.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfo, Long> {
}
