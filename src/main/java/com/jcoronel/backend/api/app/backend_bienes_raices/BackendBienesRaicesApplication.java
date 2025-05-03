package com.jcoronel.backend.api.app.backend_bienes_raices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.jcoronel.backend.api.app.backend_bienes_raices.storage.StorageProperties;
import com.jcoronel.backend.api.app.backend_bienes_raices.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BackendBienesRaicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendBienesRaicesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
}
