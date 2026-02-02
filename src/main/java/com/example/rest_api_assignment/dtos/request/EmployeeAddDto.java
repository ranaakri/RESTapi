package com.example.rest_api_assignment.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public class EmployeeAddDto {
    @NotBlank
    @Schema(example = "Krish")
    private String name;

    @Email
    @Schema(example = "krish@example.com")
    private String email;

    @Min(value = 0)
    @Schema(example = "50000")
    private float salary;

    @NotBlank
    @Schema(example = "Tech")
    private String department;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public float getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
