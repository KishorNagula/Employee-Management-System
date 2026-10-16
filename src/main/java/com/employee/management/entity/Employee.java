package com.employee.management.entity;

import jakarta.persistence.*;

@Entity // Marks this class as a database entity
@Table(name = "employees") // Maps this entity to the employees table
public class Employee {

    @Id // Marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generates the ID automatically
    private Long id;

    @Column(nullable = false) // Employee name cannot be null
    private String name;

    @Column(nullable = false, unique = true) // Email cannot be null or duplicated
    private String email;

    @Column(nullable = false) // Department cannot be null
    private String department;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}