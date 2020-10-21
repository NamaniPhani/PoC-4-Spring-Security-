package com.ojas.task;

import java.util.Optional;

import javax.management.relation.RoleResult;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ojas.task.model.UserRoles;
import com.ojas.task.repository.UserRolesRepo;

@SpringBootApplication
public class SpringSecurityPoc8Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPoc8Application.class, args);
	}

	@Bean
	CommandLineRunner init(UserRolesRepo repo) {

		return args -> {
			UserRoles role = repo.findByRole("ADMIN");
			if (role == null) {
				role = new UserRoles();
				role.setRole("ADMIN");
				repo.save(role);
			}
		};

	}
}
