package com.example.rest_api_assignment.controller;

import com.example.rest_api_assignment.dtos.request.EmployeeAddDto;
import com.example.rest_api_assignment.dtos.response.EmployeeResDto;
import com.example.rest_api_assignment.entity.EmployeeInfo;
import com.example.rest_api_assignment.services.EmployeeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/employee")
public class EmployeeControllerNewVersion {

    private final EmployeeServices employeeServices;

    public EmployeeControllerNewVersion(EmployeeServices employeeServices)
    {
        this.employeeServices = employeeServices;
    }

    @GetMapping(value = "/{id}", params = "version=1", headers = "X.1", produces = "application/vnd.company.app-v1+json")
    @Operation(
            summary = "Get Employee by id",
            description = "Fetches employee by its unique id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee Fetched Successfully"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<EmployeeResDto> getEmployee(@PathVariable long id){
        return ResponseEntity.ok(employeeServices.getEmployee(id));
    }

    @GetMapping(value = "/{id}", params = "version=2", headers = "X.2", produces = "application/vnd.company.app-v2+json")
    @Operation(
            summary = "Get Employee by id",
            description = "Fetches employee by its unique id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee Fetched Successfully"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<EmployeeResDto> getEmployeeV2(@PathVariable long id){
        return ResponseEntity.ok(employeeServices.getEmployee(id));
    }
}