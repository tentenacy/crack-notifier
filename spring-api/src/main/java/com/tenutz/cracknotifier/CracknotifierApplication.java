package com.tenutz.cracknotifier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@EnableJpaAuditing(dateTimeProviderRef = "localDateTimeProvider")
@SpringBootApplication
public class CracknotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CracknotifierApplication.class, args);
	}

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		log.info("현재시각 : " + new Date());
	}
}
