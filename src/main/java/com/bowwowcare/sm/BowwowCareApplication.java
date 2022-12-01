package com.bowwowcare.sm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BowwowCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowwowCareApplication.class, args);
	}

}
