package com.UlimaStella.Doga_Server_Demo;

import com.UlimaStella.Doga_Server_Demo.domain.Role;
import com.UlimaStella.Doga_Server_Demo.domain.User;
import com.UlimaStella.Doga_Server_Demo.services.book.AdminService;
import com.UlimaStella.Doga_Server_Demo.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DogaServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogaServerDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AdminService adminService){
		return args -> {
			adminService.saveRole(new Role(null,"ROL+E_USER"));
			adminService.saveRole(new Role(null,"ROLE_ADMIN"));
			adminService.saveRole(new Role(null,"ROLE_MANAGER"));
			adminService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			adminService.saveUser(new User(null,"Mustafa Gulsoy1","Mustafa","1234",new ArrayList<>(),new ArrayList<>()));
			adminService.saveUser(new User(null,"Mustafa Gulsoy","user","1234",new ArrayList<>(),new ArrayList<>()));
			adminService.saveUser(new User(null,"Mustafa Gulsoy","manager","1234",new ArrayList<>(),new ArrayList<>()));
			adminService.saveUser(new User(null,"Mustafa Gulsoy","superAdmin","1234",new ArrayList<>(),new ArrayList<>()));

			adminService.addRoleToUser("Mustafa","ROLE_ADMIN");
			adminService.addRoleToUser("Mustafa","ROLE_USER");
			adminService.addRoleToUser("Mustafa","ROLE_SUPER_ADMIN");
			adminService.addRoleToUser("user","ROLE_USER");
			adminService.addRoleToUser("manager","ROLE_MANAGER");
			adminService.addRoleToUser("superAdmin","ROLE_SUPER_ADMIN" );

		};
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
