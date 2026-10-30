package com.nouha.medibook;

import org.springframework.boot.SpringApplication;

public class TestMedibookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(MedibookBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
