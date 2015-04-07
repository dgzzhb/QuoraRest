/**
 * This is an unofficial Quora API written in Java.
 * @author Tian Zheng NaitGnehz@gmail.com
 * @author Gang Wu ustcwg@gmail.com
 * Carnegie Mellon University
 * March 18th, 2015
 */

package edu.cmu.cs.JQuora;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Question {
	String url;
	HashMap<String, String> question;
	// List of answers
	
	public Question(String name) throws IOException {
		this.url = new String("https://www.quora.com/" + name);
		this.question = new HashMap<String, String>();
		
		Document doc = Jsoup.connect(url).get();
		// Parse title
		String title = doc.title();
		title = title.substring(0, title.length() - 8);
		question.put("title", title);
		
		// Parse want answers 
		Element QuestionFooter = doc.select("div.action_bar_inner").first();
		String wantAnswer = QuestionFooter.select("span.count").first().text();
//		// Seperate digits and letters
//		wantAnswer = wantAnswer.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[1];
		question.put("wantAnswer", wantAnswer);
		
		// Parse number of answers
		Element answerCount = doc.select("a.active").first();
		question.put("answerCount", answerCount.text().split(" ")[0]);
			
		System.out.println(question);
	}
	
	// Get Answer from Specific Author
	public void getAnswerByAuthor(String author) throws IOException {
		// The hashmap that stores <attribute, content>
		HashMap<String, String> answer = new HashMap<String, String>();
		url += "/answer/" + author;
		Document doc = Jsoup.connect(url).get();
		
		// Author
		answer.put("author", author);
		
		parseAnswer(doc, answer);
		System.out.println(answer);
	}
	
	private void parseAnswer(Document doc, HashMap<String, String> answer) {
		if (doc == null || answer == null) return;
		
		Element answerText = doc.select("div.answer_text").first();
	
		// Text
		Element answerContent = answerText.select("div.answer_content").first();
		String text = answerContent.text();
		answer.put("text", text);
		
		// Upvote
		String upvote = doc.select("span.count").get(2).text();
		answer.put("upvote", upvote);
		
		// Comments
		String comment = doc.select("span.count").last().text();
		answer.put("comment", comment);
		
		// Url
		Element contentFooter = answerContent.select("div.AnswerFooter").first();
		Element answerFooter = doc.select("div.action_bar_inner").last();
		String answerUrl = "https://www.quora.com" + contentFooter.select("a.answer_permalink").first().attr("href");
		answer.put("url", answerUrl);
	}

	public static void main(String[] args) throws IOException {
		Question question = new Question("Are-all-the-U-S-Presidents-related-to-each-other");
		question.getAnswerByAuthor("Brian-Roemmele");
	}
}
