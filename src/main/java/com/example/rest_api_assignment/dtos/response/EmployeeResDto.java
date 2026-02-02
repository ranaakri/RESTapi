package com.example.rest_api_assignment.dtos.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeResDto {

    @NotNull
    private long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String department;

    @NotBlank
    private String profile_img_path;

    @Min(value = 0)
    private float salary;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getProfile_img_path() {
        return profile_img_path;
    }

    public float getSalary() {
        return salary;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfile_img_path(String profile_img_path) {
        this.profile_img_path = profile_img_path;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
