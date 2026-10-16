package com.employee.management.entity;

import jakarta.persistence.*;

@Entity // Marks this class as a database entity
@Table(name = "users") // Maps this entity to the users table
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generates the ID automatically
    private Long id;

    @Column(nullable = false, unique = true) // Username cannot be null or duplicated
    private String username;

    @Column(nullable = false) // Password hash cannot be null
    private String password;

    @ManyToOne(fetch = FetchType.EAGER) // Many users can share one role
    @JoinColumn(name = "role_id", nullable = false) // Stores the role's ID as a foreign key
    private Role role;

    // Default constructor required by JPA
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}