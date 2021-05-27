package com.beta.couponmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CouponMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponMicroserviceApplication.class, args);
	}

}
