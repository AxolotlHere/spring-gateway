package com.axolotl.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axolotl.DTO.AccessToken;
import com.axolotl.entity.Role;
import com.axolotl.entity.User;
import com.axolotl.jwt.JwtService;
import com.axolotl.repository.RoleRepository;
import com.axolotl.repository.UserRepository;

@Service
@Transactional
public class UserService {
   private UserRepository userRepository;
   private RoleRepository roleRepository;
   private PasswordEncoder passwordEncoder;
   private JwtService jwtServiceInstance;

   UserService(UserRepository userRepository,RoleRepository roleRepository, JwtService jwtServiceInstance){
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.passwordEncoder = new BCryptPasswordEncoder();
      this.jwtServiceInstance = jwtServiceInstance;
   }

   public User createUser(String email,String rawPwd){
      if (userRepository.findByEmail(email).isPresent()){
         throw new IllegalStateException("Account with that email already exists");
      }
      User user = new User();
      user.setEmail(email);
      user.setPassword(
         passwordEncoder.encode(rawPwd)
      );
      Role role = roleRepository.findByName("USER")
      .orElseThrow(()->new IllegalStateException("Role USER not found")
      );
      user.addRole(role);
      return userRepository.save(user);
   }
   
   public AccessToken login(String email,String rawPwd){
      User user = userRepository.findByEmail(email)
      .orElseThrow(()->new IllegalStateException("Invalid user found"));
      if(!passwordEncoder.matches(rawPwd, user.getPassword())){
         throw new IllegalAccessError("Wrong password, try again");
      }
      return jwtServiceInstance.generateTokenstring(email);
   }
}
