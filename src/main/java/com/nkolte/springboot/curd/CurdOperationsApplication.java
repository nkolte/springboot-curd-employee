package com.nkolte.springboot.curd;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@OpenAPIDefinition(
		info = @Info(
				title = "Employee Management Service",
				description = "Curd operations on Employee table",
				version = "v1.0",
				contact = @Contact(
						name = "Nitin",
						email = "Nitin@gmail.com",
						url = "www.springboot.com"
				)
		),
        externalDocs = @ExternalDocumentation(
                url = "External Docs",
				description = "demo docs"
        )

)
@SpringBootApplication
public class CurdOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurdOperationsApplication.class, args);
	}

	@Bean
	ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
