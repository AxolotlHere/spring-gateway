package com.axolotl.DTO;

import javax.validation.constraints.NotNull;

public class AccessToken {
   @NotNull
   private String token;

   public AccessToken(String token){
      this.token = token;
   }

   public String getToken(){
      return token;
   }

}
