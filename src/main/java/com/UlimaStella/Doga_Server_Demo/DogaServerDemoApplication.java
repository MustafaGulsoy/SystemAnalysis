package com.UlimaStella.Doga_Server_Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DogaServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogaServerDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null,"Mustafa Gulsoy1","Mustafa","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Mustafa Gulsoy","user","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Mustafa Gulsoy","manager","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Mustafa Gulsoy","superAdmin","1234",new ArrayList<>()));

			userService.addRoleToUser("Mustafa","ROLE_ADMIN");
			userService.addRoleToUser("Mustafa","ROLE_USER");
			userService.addRoleToUser("Mustafa","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("user","ROLE_USER");
			userService.addRoleToUser("manager","ROLE_MANAGER");
			userService.addRoleToUser("superAdmin","ROLE_SUPER_ADMIN" );

		};
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
