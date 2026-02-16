package com.axolotl.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axolotl.DTO.AccessToken;
import com.axolotl.DTO.LoginReq;
import com.axolotl.DTO.SignupReq;
import com.axolotl.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
   private final UserService userService;
   public AuthController(UserService userService){
      this.userService = userService;
   }
   @PostMapping("/signup")
   public void signup(@RequestBody @Valid SignupReq signupReq){
      userService.createUser(signupReq.getEmail(), signupReq.getPassword());
   }
   @PostMapping("/login")
   public AccessToken login(@RequestBody @Valid LoginReq loginReq){
      return userService.login(loginReq.getEmail(), loginReq.getPassword());
   }

}
