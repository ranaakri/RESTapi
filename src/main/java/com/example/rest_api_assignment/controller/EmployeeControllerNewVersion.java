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
//Employee Controller Version 2
@RequestMapping("api/v2/employee")
public class EmployeeControllerNewVersion {

    private final EmployeeServices employeeServices;

    public EmployeeControllerNewVersion(EmployeeServices employeeServices)
    {
        this.employeeServices = employeeServices;
    }

    //Get Employee by Id
    @GetMapping(value = "/{id}")
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

    //Versioning example 3
    @GetMapping(value = "/hello", headers = "VERSION=X.0.1")
    public ResponseEntity<String> getHelloV1(){
        return ResponseEntity.ok("Hello V1");
    }

    @GetMapping(value = "/hello", headers = "VERSION=X.0.2")
    public ResponseEntity<String> getHelloV2(){
        return ResponseEntity.ok("Hello V2");
    }


    //Versioning example 4
    @GetMapping(value = "/hello/produces", produces = "application/vnd.myapp.v1+json")
    public ResponseEntity<String> getHelloProV1(){
        return ResponseEntity.ok("Hello V1");
    }

    @GetMapping(value = "/hello/produces", produces = "application/vnd.myapp.v2+json")
    public ResponseEntity<String> getHelloProV2(){
        return ResponseEntity.ok("Hello V2");
    }
}