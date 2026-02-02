package com.example.rest_api_assignment.entity;

import jakarta.persistence.*;

@Entity
public class EmployeeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String department;
    private float salary;
    private String profile_img_path;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public float getSalary() {
        return salary;
    }

    public String getProfile_img_path() {
        return profile_img_path;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setProfile_img_path(String profile_img_path) {
        this.profile_img_path = profile_img_path;
    }
}
