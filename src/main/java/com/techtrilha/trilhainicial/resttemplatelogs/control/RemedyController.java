package com.techtrilha.trilhainicial.resttemplatelogs.control;

import com.techtrilha.trilhainicial.resttemplatelogs.remedy.Remedy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@RestController
public class RemedyController {

  @PostMapping("/remedies")
  public ResponseEntity<Remedy> generateRemedy(@RequestBody Remedy remedy) {
    remedy.setId(1L);
    return ResponseEntity
        .created(URI.create("/remedies/" + remedy.getId()))
        .body(remedy);
  }

}
