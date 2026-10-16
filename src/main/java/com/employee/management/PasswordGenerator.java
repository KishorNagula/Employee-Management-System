package com.employee.management;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();
        Scanner sc=new Scanner(System.in);
        System.out.println("enter Uname and password:");
        String Uname=sc.next();
        String password=sc.next();

        System.out.println(Uname + encoder.encode(password));
        
    }
}