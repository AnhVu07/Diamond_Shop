package com.anhvu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootDiamondShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDiamondShopApplication.class, args);
		
	}

}
