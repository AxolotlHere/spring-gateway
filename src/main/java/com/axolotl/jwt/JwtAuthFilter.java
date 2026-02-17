package com.axolotl.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.axolotl.DTO.AccessToken;
import com.axolotl.entity.User;
import com.axolotl.service.UserService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

   private final JwtService jwtService;
   private final UserService userService;

   public JwtAuthFilter(JwtService jwtService,UserService userService){
      this.jwtService = jwtService;
      this.userService = userService;
   }

   @Override
   protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
   ) throws IOException, ServletException{
      String header = request.getHeader("Authorization");

      if(header==null||!header.startsWith("Bearer ")){
         filterChain.doFilter(request, response);
         return;
      }
      String token = header.substring(7);
      String email = jwtService.extractClaims(new AccessToken(token));
      UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                    email, null, Collections.emptyList()
                );
      SecurityContextHolder.getContext().setAuthentication(auth);
      filterChain.doFilter(request, response);
   }
}
