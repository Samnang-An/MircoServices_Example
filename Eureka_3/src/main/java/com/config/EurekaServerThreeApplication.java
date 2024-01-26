package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerThreeApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaServerThreeApplication.class, args);
  }

}
