package com.example.rest_api_assignment.services;

import com.example.rest_api_assignment.dtos.request.EmployeeAddDto;
import com.example.rest_api_assignment.dtos.response.EmployeeResDto;
import com.example.rest_api_assignment.entity.EmployeeInfo;
import com.example.rest_api_assignment.exception.ResourceNotFoundException;
import com.example.rest_api_assignment.repository.EmployeeInfoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {
    private final EmployeeInfoRepo employeeInfoRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServices(EmployeeInfoRepo employeeInfoRepo, ModelMapper modelMapper){
        this.employeeInfoRepo = employeeInfoRepo;
        this.modelMapper = modelMapper;
    }

    //Get List of employees
    public List<EmployeeResDto> getEmployeeList(){
        List<EmployeeInfo> employees = employeeInfoRepo.findAll();

        return employees.stream().map(emp ->
                    modelMapper.map(emp, EmployeeResDto.class)
                ).toList();
    }

    //Get employee by id
    public EmployeeResDto getEmployee(long id) {

        //Throws global exception if id not found
        EmployeeInfo data = employeeInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        return modelMapper.map(data, EmployeeResDto.class);
    }

    //Insert new employee
    public EmployeeResDto addEmployee(EmployeeAddDto employee){
        EmployeeInfo empData = modelMapper.map(employee, EmployeeInfo.class);
        return modelMapper.map(employeeInfoRepo.save(empData), EmployeeResDto.class);
    }

    //Update Employee
    public EmployeeResDto updateEmployee(long id, EmployeeResDto employee){

        //Throws global exception if id not found
        EmployeeInfo emp = employeeInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + employee.getId()));

        emp.setDepartment(employee.getDepartment());
        emp.setEmail(employee.getEmail());
        emp.setName(employee.getName());
        emp.setProfile_img_path(employee.getProfile_img_path());
        emp.setSalary(employee.getSalary());

        return modelMapper.map(employeeInfoRepo.save(emp), EmployeeResDto.class);
    }

    //Update Department of employee
    public EmployeeResDto updateDepartment(long id, String department){

        //Throws global exception if Id not found
        EmployeeInfo emp = employeeInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        emp.setDepartment(department);

        return  modelMapper.map(employeeInfoRepo.save(emp), EmployeeResDto.class);
    }

    //Delete employee by id
    public EmployeeResDto deleteEmployee(long id){

        //Throws global exception if Id not found
        EmployeeInfo emp = employeeInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        employeeInfoRepo.delete(emp);
        return modelMapper.map(emp, EmployeeResDto.class);
    }
}