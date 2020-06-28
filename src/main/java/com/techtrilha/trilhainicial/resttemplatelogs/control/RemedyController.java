package com.techtrilha.trilhainicial.resttemplatelogs.control;

import com.techtrilha.trilhainicial.resttemplatelogs.remedy.Remedy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@RestController
public class RemedyController {

  private final RestTemplate httpClient;

  public RemedyController(RestTemplate httpClient) {
    this.httpClient = httpClient;
  }

  @PostMapping("/remedies")
  public ResponseEntity<Remedy> generateRemedy(@RequestBody Remedy remedy) {
    final var pharmaceuticalsLocation = "http://localhost:8080/pharmaceuticals/";
    final var url = pharmaceuticalsLocation + remedy.getPharmaceuticalId();
    final var pharmaceuticalResponse = httpClient.getForEntity(url, String.class);
    if (pharmaceuticalResponse.getStatusCode().isError()) {
      ResponseEntity.badRequest().build();
    }
    remedy.setId(1L);
    return ResponseEntity
        .created(URI.create("/remedies/" + remedy.getId()))
        .body(remedy);
  }

}
