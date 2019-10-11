package com.caseStudy.Khareedo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude ={SecurityAutoConfiguration.class})
public class KhareedoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhareedoApplication.class, args);
	}

}
