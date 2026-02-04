package com.example.rest_api_assignment.repository;

import com.example.rest_api_assignment.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

//Employee Repository
public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfo, Long> {
}
