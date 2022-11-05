package com.orderp.OrdERP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.orderp.OrdERP")
public class OrdErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdErpApplication.class, args);
	}

}
