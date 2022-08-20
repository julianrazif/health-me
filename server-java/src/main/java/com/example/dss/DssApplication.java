package com.example.dss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.example.dss"})
public class DssApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder
      .sources(DssApplication.class)
      .headless(true)
      .web(WebApplicationType.SERVLET);
  }

  public static void main(String[] args) {
    SpringApplication.run(DssApplication.class, args);
  }
}
