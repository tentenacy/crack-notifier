package com.tenutz.cracknotifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CracknotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CracknotifierApplication.class, args);
	}

}
