package com.techtrilha.trilhainicial.resttemplatelogs.config;

import lombok.Getter;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Getter
class RestTemplateLog {
  private Map<String, String> request = new HashMap<>();
  private Map<String, String> response = new HashMap<>();

  public RestTemplateLog(ClientHttpResponse response, HttpRequest request, byte[] body) {
    fillRequestInfo(request, body);
    fillResponseInfo(response);
  }

  private void fillRequestInfo(HttpRequest request, byte[] body) {
    final var requestUri = request.getURI();
    final var requestMethod = request.getMethod();
    final var requestHeaders = request.getHeaders();
    var requestBody = "";
    try {
      requestBody = new String(body, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    this.request.put("uri", requestUri.toString());
    this.request.put("method", requestMethod.toString());
    this.request.put("headers", requestHeaders.toString());
    this.request.put("body", requestBody);
  }

  private void fillResponseInfo(ClientHttpResponse response) {
    var responseStatus = "";
    var responseHeaders = response.getHeaders().toString();
    var responseBody = "";
    try {
      responseStatus = response.getStatusCode().toString();
      responseBody = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.response.put("status", responseStatus);
    this.response.put("headers", responseHeaders);
    this.response.put("body", responseBody);
  }
}
