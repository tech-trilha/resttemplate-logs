package com.techtrilha.trilhainicial.resttemplatelogs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateClientConfig {

  private final RestTemplateErrorInterceptor errorInterceptor;

  @Bean
  public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
    return builder
        .requestFactory(
            () -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
        .additionalInterceptors(errorInterceptor)
        .build();
  }

}
