package edu.cmu.cs.QuoraRest;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.GET;  
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType; 

import org.json.simple.JSONObject;

import edu.cmu.cs.JQuora.*;

@Path("user")  
public class User {
	@SuppressWarnings("unchecked")
	@GET  
	@Path("/{username}")    
	@Produces(MediaType.APPLICATION_JSON)   
	public String getActivity(@PathParam("username")  
	String username) throws IOException {
		UserInfo user = new UserInfo(username);
		user.init();
		

		JSONObject obj = new JSONObject();
		HashMap<String, Integer> map = user.getActivity_count();
		for (String key : map.keySet()) {
			obj.put(key, map.get(key));
		}
		
		return obj.toJSONString();
	}
	
	
} 