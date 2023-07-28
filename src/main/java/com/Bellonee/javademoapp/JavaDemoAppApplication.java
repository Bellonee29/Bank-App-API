package com.Bellonee.javademoapp;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "The Java Bellonee Bank App",
				description = "Backend Rest APIs for Bellonee Bank",
				version = "v1.8",
				contact = @Contact(
						name = "Wajiu Bello Olarewaju",
						email = "bellowajiuo@gmail.com",
						url = "https://github.com/Bellonee29/Bellonee_Bank_App"
				),
				license = @License(
						name = "Bellonee Squad",
						url = "https://github.com/Bellonee29/Bellonee_Bank_App"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Backend Rest APIs for Bellonee Bank",
				url = "https://github.com/Bellonee29/Bellonee_Bank_App"
		)

)
public class JavaDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaDemoAppApplication.class, args);
	}

}
