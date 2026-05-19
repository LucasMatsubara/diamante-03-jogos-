package br.com.fiap.diamondgames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DiamondgamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiamondgamesApplication.class, args);
	}

}
