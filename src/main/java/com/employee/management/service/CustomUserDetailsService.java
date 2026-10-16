package com.employee.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.employee.management.entity.User;
import com.employee.management.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	 @Autowired
	 UserRepository userRepository;
	 
    @Override
    public UserDetails loadUserByUsername(String username) {

    	  User user = userRepository.findByUsername(username)
                  .orElseThrow(() -> new RuntimeException("User not found"));

          return org.springframework.security.core.userdetails.User
                  .withUsername(user.getUsername())
                  .password(user.getPassword())
                  .authorities( user.getRole()
                                .getPrivileges()
                                .stream()
                                .map(privilege -> privilege.getPrivilegeName())
                                .toArray(String[]::new))
                                .build();
    }
}