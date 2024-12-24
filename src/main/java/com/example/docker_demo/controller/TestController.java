package com.example.docker_demo.controller;

import java.time.LocalDateTime;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
  private final RedisTemplate<String, String> redisTemplate;

  record TestResponse(String message, LocalDateTime timestamp) {
  }

  @GetMapping("/test")
  public TestResponse test() {
    return new TestResponse("Docker Test with Java 21!", LocalDateTime.now());
  }

  @PostMapping("/redis-test")
  public String redisTest(@RequestBody String value) {
    String result = switch (value) {
      case String s when s.isBlank() -> "Empty String";
      case String s -> {
        redisTemplate.opsForValue().set("test-key", s);
        yield "Saved to Redis: " + s;
      }
    };
    System.out.println(result);
    return result;
  }

  @GetMapping("/redis-test")
  public String redisTest() {
    return redisTemplate.opsForValue().get("test-key");
  }
}
