package com.GuRiSmartObras.GuRiSmartObras;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SmartObras", description = "Projeto criado por Guilherme Sonsin e Ricardo Vinciguerra."))
public class GuRiSmartObrasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuRiSmartObrasApplication.class, args);
	}
}