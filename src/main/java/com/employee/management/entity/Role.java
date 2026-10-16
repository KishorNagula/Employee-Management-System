package com.employee.management.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // Marks this class as a database entity
@Table(name = "roles") // Maps this entity to the roles table
public class Role {

    @Id // Marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generates the ID automatically
    private Long id;

    @Column(nullable = false, unique = true) // Role name must be unique
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER) // A role can have multiple privileges
    @JoinTable(
        name = "role_privileges", // Name of the relationship table
        joinColumns = @JoinColumn(name = "role_id"), // Foreign key referencing Role
        inverseJoinColumns = @JoinColumn(name = "privilege_id") // Foreign key referencing Privilege
    )
    private Set<Privilege> privileges = new HashSet<>();

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}