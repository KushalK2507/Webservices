package com.kushal.spring.security.jwt.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GeneratorUtils {

  private GeneratorUtils() {}

  public String clientIdGenerator(String clientName, String token) {
    try {
      String[] parts = new String[] {clientName, token};
      var has =
          MessageDigest.getInstance("SHA-256")
              .digest(String.join(":", parts).getBytes(StandardCharsets.UTF_8));
      return Arrays.toString(Hex.encode(has)).toUpperCase();
    } catch (NoSuchAlgorithmException exception) {
      log.error("Error in creating clientId", exception);
    }
    return null;
  }
}
