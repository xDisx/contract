package com.xdisx.contract.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories
public class ContractApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContractApplication.class, args);
  }
}
