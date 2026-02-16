package com.axolotl.jwt;

import java.util.Date;

import javax.persistence.Access;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.axolotl.DTO.AccessToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JwtService {
   @Value("${jwt.secret}")
   public String secret;
   
   @Value("${jwt.expiration-ms}")
   public long expiration;

   public AccessToken generateTokenstring(String email){
      return new AccessToken(Jwts.builder()
      .setSubject(email)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis()+expiration))
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact());
   }

   public String extractClaims(AccessToken token){
      return Jwts.parser()
      .setSigningKey(secret)
      .parseClaimsJwt(token.getToken())
      .getBody()
      .getSubject();
   }
   
}
