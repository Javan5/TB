package com.TB.TB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TbApplication.class, args);
	}

}
