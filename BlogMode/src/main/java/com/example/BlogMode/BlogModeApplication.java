package com.example.BlogMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogModeApplication implements CommandLineRunner {

	@Autowired
	private  PasswordEncoder passwordEncoder;

	public static void main(String[] args) {

		SpringApplication.run(BlogModeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("123"));
	}
}
