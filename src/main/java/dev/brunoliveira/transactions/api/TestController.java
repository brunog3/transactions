package dev.brunoliveira.transactions.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test")
  public ResponseEntity<ResponseTest> get() {
    return ResponseEntity.ok(ResponseTest.builder().code("CODE_001").build());
  }

  @Data
  @Builder
  public static class ResponseTest {
    private String code;
  }
}
