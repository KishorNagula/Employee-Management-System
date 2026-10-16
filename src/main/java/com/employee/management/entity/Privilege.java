package com.employee.management.entity;

import jakarta.persistence.*;

@Entity // Marks this class as a database entity
@Table(name = "privileges") // Maps this entity to the privileges table
public class Privilege {

    @Id // Marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generates the ID automatically
    private Long id;

    @Column(nullable = false, unique = true) // Privilege name must be unique
    private String privilegeName;

    public Privilege() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }
}