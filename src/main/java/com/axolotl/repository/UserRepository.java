package com.axolotl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axolotl.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
   public Optional<User> findByEmail(String email);
   public Optional<User> findByName(String name);
   public Optional<User> findById(Long id);
}
