package com.ride.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.ride.admin.mapper")
@SpringBootApplication
public class AdminBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminBackendApplication.class, args);
	}

}


