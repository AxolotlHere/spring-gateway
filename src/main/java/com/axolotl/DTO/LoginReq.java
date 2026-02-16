package com.axolotl.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginReq {
   @Email(message = "Invalid email")
   @NotBlank(message = "Email can not be blank")
   private String email;

   @NotBlank(message = "Password can not be blank")
   @Size(min=6,message = "Password should atleast be 6 characters long")
   private String password;

   public String getEmail(){
      return this.email;
   }
   public String getPassword(){
      return this.password;
   }
   public void setEmail(String email){
      this.email = email;
   }
   public void setPassword(String password){
      this.password = password;
   }
}
