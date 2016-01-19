package demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class FileAccessController {

   IOneDrive oneDrive = new MockOneDrive();


   private String getUserName(OAuth2Authentication auth)
   {
      Object userDetails = auth.getUserAuthentication().getDetails();
      String userName = "unknown User";
      if(userDetails instanceof LinkedHashMap)
      {
         LinkedHashMap<?,?> uDetails = (LinkedHashMap<?, ?>) userDetails;
         userName = (String) uDetails.get("name");
      }
      return userName;
   }

   @RequestMapping("/unprotected")
   public String getUnprotected() {

      return "A place holder that can go away";
   }

   @RequestMapping("/getDocument/{name}")
   public String getDocument(OAuth2Authentication auth, @PathVariable("name") String name) {

      return "Hello, " + getUserName(auth) + ". We will soon implement getDocument : " + name;
   }


   @RequestMapping("/doclist")
   public String docList(OAuth2Authentication auth, Model model) {

      return "Hello, " + getUserName(auth) + ". We will soon implement doclist";
   }



}
