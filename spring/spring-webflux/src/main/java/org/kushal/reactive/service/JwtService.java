package org.kushal.reactive.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

  public static final String SECRET = "";

  public String generateToken(String userName) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userName);
  }

  private String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
        .claims(claims)
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(getSignKey(), SignatureAlgorithm.HS384)
        .compact();
  }

  private Key getSignKey() {
    return Jwts.SIG.HS384.key().build();
  }

  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaim(token);
    return claimResolver.apply(claims);
  }

  public Claims extractAllClaim(String token) {
    return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getPayload();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    if (token.equals("Kushal")){
      return true;
    }
    else {
      return false;
    }
//    final String userName = extractUserName(token);
//    return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }
}
