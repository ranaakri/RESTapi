package com.example.rest_api_assignment.controller;

import com.example.rest_api_assignment.dtos.request.EmployeeAddDto;
import com.example.rest_api_assignment.dtos.response.EmployeeResDto;
import com.example.rest_api_assignment.entity.EmployeeInfo;
import com.example.rest_api_assignment.services.EmployeeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices)
    {
        this.employeeServices = employeeServices;
    }

    //Fetch List of Employees
    @GetMapping("/list")
    @Operation(
            summary = "Get List of employees",
            description = "Get List of employees",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employees fetched successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<List<EmployeeResDto>> getEmployeeList(){
        return ResponseEntity.ok(employeeServices.getEmployeeList());
    }

    //Fetch Employee by Id Version 1 using Param versioning
    @GetMapping(value = "/{id}", params = "version=1")
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

    //Fetch Employee by Id Version 2 using Param Versioning
    @GetMapping(value = "/{id}", params = "version=2")
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

    //Update Employee
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Employee by id",
            description = "Updates employee by its unique id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee Updated Successfully"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<EmployeeResDto> updateEmployee(@PathVariable long id, @Valid @RequestBody EmployeeResDto employee){
        return ResponseEntity.ok(employeeServices.updateEmployee(id, employee));
    }

    //Update Department of an Employee
    @PatchMapping("/{id}")
    @Operation(
            summary = "Patch Employee department by id",
            description = "Updates  only employee department by its unique id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Department updated Successfully"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<EmployeeResDto> updateDepartment(@PathVariable long id, @RequestParam String department){
        return ResponseEntity.ok(employeeServices.updateDepartment(id, department));
    }

    //Add new Employee
    @PostMapping("/add")
    @Operation(
            summary = "Adds Employee",
            description = "Add new employee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Employee Created Successfully"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<EmployeeResDto> addEmployee(@Valid @RequestBody EmployeeAddDto employee){
        return new ResponseEntity<>(employeeServices.addEmployee(employee),HttpStatus.CREATED);
    }

    //Delete Employee by Id
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Employee by id",
            description = "Delete employee by its unique id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee deleted Successfully"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<EmployeeResDto> deleteEmployee(@PathVariable int id){
        return new ResponseEntity<>(employeeServices.deleteEmployee(id), HttpStatus.OK);
    }
}
