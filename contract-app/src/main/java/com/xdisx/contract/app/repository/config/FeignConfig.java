package com.xdisx.contract.app.repository.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  public RequestInterceptor requestInterceptor() {
    return template -> template.header("X-Internal-Request", "contract-service");
  }
}