package com.cloudlearn.jichengfeign3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class Jichengfeign3Application {

	public static void main(String[] args) {
		SpringApplication.run(Jichengfeign3Application.class, args);
	}
}
