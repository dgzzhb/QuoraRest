package edu.cmu.cs.QuoraRest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.*;
import edu.cmu.cs.JQuora.*;

@Path("question")
public class Question {
	
	@GET
	@Path("/{i}")
	@Produces(MediaType.APPLICATION_JSON)
	public String userName(@PathParam("i") String i) throws IOException {

		QuestionInfo question = new QuestionInfo(i);
		JSONObject obj = new JSONObject();
		obj.put("answerCount", question.questionMap.get("answerCount"));
		obj.put("wantAnswer", question.questionMap.get("wantAnswer"));
		obj.put("title", question.questionMap.get("title"));
		return obj.toJSONString();
	}

}