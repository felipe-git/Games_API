package com.felipe.gamesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.felipe"})
public class GamesappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesappApplication.class, args);
	}

}

