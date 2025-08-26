package com.pruebatecnica.demo.security;

import com.pruebatecnica.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserService userService;
    
    @Override
    public void run(String... args) throws Exception {
        // Crear usuario administrador por defecto
        userService.initializeDefaultUser();
        System.out.println("Usuario administrador inicializado: admin/admin123");
    }
}