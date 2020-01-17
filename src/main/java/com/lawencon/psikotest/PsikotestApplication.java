package com.lawencon.psikotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.lawencon.psikotest.dao",
		"com.lawencon.psikotest.service",
		"com.lawencon.psikotest.controller",
		"com.lawencon.psikotest.config"})
@EnableTransactionManagement
@EntityScan(basePackages = "com.lawencon.psikotest.entity")
public class PsikotestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsikotestApplication.class, args);
	}

}
