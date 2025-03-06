package com.lazarnisic.ParkSmart;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Slf4j
public class ParkSmartApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void checkSecurityConfig() {
		boolean isBeanPresent = applicationContext.containsBean("securityConfig");
		log.info("Is SecurityConfig bean present: " + isBeanPresent);
	}
	public static void main(String[] args) {
		SpringApplication.run(ParkSmartApplication.class, args);
	}

}
