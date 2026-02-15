package com.axolotl.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.axolotl.entity.Role;
import com.axolotl.entity.User;
import com.axolotl.repository.RoleRepository;
import com.axolotl.repository.UserRepository;

@Service
@Transactional
public class UserService {
   private UserRepository userRepository;
   private RoleRepository roleRepository;

   UserService(UserRepository userRepository,RoleRepository roleRepository){
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
   }

   public User createUser(String email,String rawPwd){
      if (userRepository.findByEmail(email).isPresent()){
         throw new IllegalStateException("Account with that email already exists");
      }
      User user = new User();
      user.setEmail(email);
      user.setPassword(rawPwd);
      Role role = roleRepository.findByName("USER")
      .orElseThrow(()->new IllegalStateException("Role USER not found")
      );
      user.addRole(role);
      return userRepository.save(user);
   }
   
   public User getUser(Long id){
      return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
   }

}
