package com.example.cruiseroyalebe;

import com.example.cruiseroyalebe.entity.Role;
import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class CruiseRoyaleBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruiseRoyaleBeApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//
//	@Bean
//	CommandLineRunner run (UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//			userService.saveUser(new User(null, "Duong tung", "duongtt", "123","email","phone", "address",new ArrayList<>()));
//			userService.saveUser(new User(null, "Lanh", "lnla", "123","email","phone", "address",new ArrayList<>()));
//			userService.saveUser(new User(null, "Thuy Chi", "ttc", "123","email","phone", "address",new ArrayList<>()));
//			userService.saveUser(new User(null, "Minh Tam", "mt", "123","email","phone", "address",new ArrayList<>()));
//			userService.saveUser(new User(null, "Truc Kim", "kt", "123","email","phone", "address",new ArrayList<>()));
//
//			userService.addRoleToUser("duongtt", "ROLE_USER");
//			userService.addRoleToUser("duongtt", "ROLE_MANAGER");
//			userService.addRoleToUser("duongtt", "ROLE_ADMIN");
//
//
//		};
//	}
}
