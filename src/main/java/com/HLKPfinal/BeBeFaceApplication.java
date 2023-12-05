package com.HLKPfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BeBeFaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeBeFaceApplication.class, args);
	}

}
