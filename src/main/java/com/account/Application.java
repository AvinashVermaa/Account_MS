package com.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.account.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				version = "v1",
				title = "Accounts microservice REST API Documentation",
				summary = "Summary for the EazyBank Accounts microservice REST API Documentation",
				description = "EazyBank Accounts microservice REST API Documentation",
				contact = @Contact(
						name = "Avinash Verma",
						url = "www.google.com",
						email = "avinash@outlook.com"
						)
				,
				termsOfService = "Terms & Condition Applies"
				)
		,externalDocs = @ExternalDocumentation(
				description = "EazyBank Accounts microservice REST API Documentation",
				url = "http://localhost:8080/swagger-ui/index.html"
				)
		)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
