package edu.cmu.cs.QuoraRest;

import java.io.IOException;

import javax.ws.rs.GET;  
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType; 

import edu.cmu.cs.JQuora.*;

@Path("UserInfoService")  
public class UserInfo {  
 @GET  
 @Path("/name/{i}")    
 @Produces(MediaType.TEXT_XML)   
 public String userName(@PathParam("i")  
 String i) throws IOException {  
	 User user = new User("Gang-Wu");
	 user.init();
     String name = i;  
     return "<User>" + "<Name>" + user.toString() + "</Name>" + "</User>";  
 }  

} 