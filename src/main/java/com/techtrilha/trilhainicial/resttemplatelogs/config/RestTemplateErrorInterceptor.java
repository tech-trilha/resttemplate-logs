package com.techtrilha.trilhainicial.resttemplatelogs.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.nio.charset.Charset;

@Log4j2
public class RestTemplateErrorInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                      ClientHttpRequestExecution execution) throws IOException {
    log.info("Executando uma requisicao do tipo {} para a URL {}", request.getMethod(),
        request.getURI());
    final var response = execution.execute(request, body);
    log.info("O codigo de status HTTP da resposta  foi {}", response.getStatusCode());
    final var responseBody = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
    log.info("O corpo da resposta  foi {}", responseBody);
    return response;
  }
}
