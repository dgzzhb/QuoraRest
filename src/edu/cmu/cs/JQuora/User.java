/**
 * This is an unofficial Quora API written in Java.
 * @author Tian Zheng NaitGnehz@gmail.com
 * @author Gang Wu ustcwg@gmail.com
 * Carnegie Mellon University
 * March 18th, 2015
 */

package edu.cmu.cs.JQuora;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class User {
	private String user_name;
	private String url;
	
	private String last_name;
	private String first_name;

	private HashMap<String, Integer> activity_count;
	
	public HashMap<String, Integer> getActivity_count() {
		return activity_count;
	}


	public void setActivity_count(HashMap<String, Integer> activity_count) {
		this.activity_count = activity_count;
	}

	private Document doc;
	
	public User(String name) {
		user_name = name;
		url = "http://www.quora.com/" + user_name;
		doc = null;
		
		last_name = null;
		first_name = null;
		activity_count = null;
	}
	
	
	public boolean init() {
		try {
			doc = Jsoup.connect(url).get();
			
			String[] name = doc.title().split("-")[0].trim().split(" ");
			
			last_name = name[0];
			first_name = name[1];
			
			System.out.println(last_name + " " + first_name);
			
			activity_count = new HashMap<String, Integer>() {{
				put("Questions", 0);
				put("Answers", 0);
				put("Posts", 0);
				put("Followers", 0);
				put("Following", 0);
				put("Edits", 0);
			}};
			
			
			Elements link = doc.select("a[href*=/" + user_name + "]");
			
			for (String key : link2attr.keySet()) {
				Elements attr_elems = link.select("a[href*=/" + user_name + "/" + key + "]");
				if (attr_elems.size() > 0) {
					int value = Integer.parseInt(attr_elems.select("div.value").text().replace(",",""));
					activity_count.put(link2attr.get(key), value);
				}
			}
			
		
			System.out.println(activity_count);
			
			return true;
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
	private static HashMap<String, String> link2attr = new HashMap<String, String>() {{
		put("questions", "Questions");
		put("answers", "Answers");
		put("all_posts", "Posts");
		put("followers", "Followers");
		put("following", "Following");
		put("log","Edits");
	}};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User("Gang-Wu");
		user.init();
		
	}

}
