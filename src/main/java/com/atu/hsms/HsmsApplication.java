package com.atu.hsms;

import com.atu.hsms.domain.Role;
import com.atu.hsms.services.book.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsmsApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(AdminService adminService){
//		return args -> {
//			adminService.saveRole(new Role(null, "ROLE_USER"));
//			adminService.saveRole(new Role(null, "ROLE_ADMIN"));
//			adminService.saveRole(new Role(null, "ROLE_MANAGER"));
//			adminService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//
//			adminService.addRoleToUser("Mustafa","ROLE_ADMIN");
//			adminService.addRoleToUser("Mustafa","ROLE_USER");
//			adminService.addRoleToUser("Mustafa","ROLE_SUPER_ADMIN");
//			adminService.addRoleToUser("user","ROLE_USER");
//			adminService.addRoleToUser("manager","ROLE_MANAGER");
//			adminService.addRoleToUser("superAdmin","ROLE_SUPER_ADMIN" );
//
//		};
//	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
