package com.axolotl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axolotl.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
