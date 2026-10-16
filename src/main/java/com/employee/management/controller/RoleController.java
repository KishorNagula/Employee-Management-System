package com.employee.management.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.employee.management.entity.Privilege;
import com.employee.management.entity.Role;
import com.employee.management.repository.PrivilegeRepository;
import com.employee.management.repository.RoleRepository;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @PostMapping("/{roleId}/privileges/{privilegeId}")
    @PreAuthorize("hasAuthority('MANAGE_PRIVILEGES')")
    public String assignPrivilege(
            @PathVariable Long roleId,
            @PathVariable Long privilegeId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Privilege privilege = privilegeRepository.findById(privilegeId)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));

        role.getPrivileges().add(privilege);

        roleRepository.save(role);

        return "Privilege assigned successfully";
    }
    
    @GetMapping("/{roleId}/privileges")
    @PreAuthorize("hasAuthority('MANAGE_PRIVILEGES')")
    public Set<Privilege> getPrivileges(@PathVariable Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return role.getPrivileges();
    }
    
    @DeleteMapping("/{roleId}/privileges/{privilegeId}")
    @PreAuthorize("hasAuthority('MANAGE_PRIVILEGES')")
    public String removePrivilege(
            @PathVariable Long roleId,
            @PathVariable Long privilegeId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Privilege privilege = privilegeRepository.findById(privilegeId)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));

        role.getPrivileges().remove(privilege);

        roleRepository.save(role);

        return "Privilege removed successfully";
    }
}