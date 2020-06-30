package com.techtrilha.trilhainicial.resttemplatelogs.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class RestTemplateErrorInterceptor implements ClientHttpRequestInterceptor {

  private final ObjectMapper objectMapper;

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                      ClientHttpRequestExecution execution) throws IOException {
    final var response = execution.execute(request, body);
    logErrorResponse(response, request, body);
    return response;
  }

  private void logErrorResponse(ClientHttpResponse response, HttpRequest request, byte[] body) {
    try {
      if (response.getStatusCode().isError()) {
        final var errorLog = getErrorMessage(response, request, body);
        log.error("Erro ao acessar servico externo = {}", errorLog);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getErrorMessage(ClientHttpResponse response, HttpRequest request, byte[] body)
      throws JsonProcessingException {
    final var errorLog = new RestTemplateLog(response, request, body);
    return objectMapper.writeValueAsString(errorLog);
  }

}
