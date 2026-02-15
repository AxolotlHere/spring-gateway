package com.axolotl.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="users")
public class User {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Email
   @Column(nullable = false,unique = true)
   private String email;

   @NotNull
   private String password;

   @Column(updatable = false)
   private Instant createdAt = Instant.now();

   private Instant lastLogin;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
      name="user_role",
      joinColumns = @JoinColumn(name="user_id"),
      inverseJoinColumns = @JoinColumn(name="role_id")
   )
   private Set<Role> roles = new HashSet<>();

   public void setEmail(String email){
      this.email = email;
   }
   public void setPassword(String passwd){
      this.password = passwd;
   }
   public String getEmail(){
      return email;
   }
   public String getPassword(){
     return password;
   }
   public void addRole(Role role){
      roles.add(role);
   }

}
