package com.axolotl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Permissions")
public class Permission {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public long id;

   @Column(nullable = false,unique = true)
   private String name; //USER_READ

   @Column(nullable = false)
   private String resource; //USER 

   @Column(nullable = false)
   private String action; //READ


}
