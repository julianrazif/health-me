package com.example.dss.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@EnableAsync
@Configuration
public class ApplicationConfig {

  @Bean
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    int processorCount = Runtime.getRuntime().availableProcessors();
    executor.setCorePoolSize(processorCount);
    executor.setMaxPoolSize(processorCount);
    executor.setThreadNamePrefix("Julian-");
    executor.initialize();
    return executor;
  }

  @Bean
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (ex, method, params) -> {
      log.error("Exception message - {}", ex.getMessage());
      log.error("Method name - {}", method.getName());
      for (Object param : params) {
        log.error("Parameter value - {}", param);
      }
    };
  }

  @Bean
  public CommandLineRunner runner() {
    return args -> log.info("Start app . . .");
  }
}
