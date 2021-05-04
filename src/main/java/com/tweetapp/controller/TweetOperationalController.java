package com.tweetapp.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.userTweets;
import com.tweetapp.service.TweetService;

@RestController
@CrossOrigin
@RequestMapping({"/tweets"})
public class TweetOperationalController {

	
	TweetService tweetService=new TweetService();
	
	public boolean nameValidator(String name)
	{
		 String regx = "^[\\p{L} .'-]+$";
		    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(name);
		  return matcher.find();
	}
	public boolean genderValidator(String name)
	{
		return (name.equalsIgnoreCase("M")|| name.equalsIgnoreCase("F") || name.equalsIgnoreCase("NB"))?true:false;
			
	}
	public boolean emailValidator(String email)
	{
		 String regx = "^[A-Za-z0-9+_.-]+@(.+){3}$";
		    Pattern pattern = Pattern.compile(regx);
		    Matcher matcher = pattern.matcher(email);
		  return matcher.find();
	}
	public boolean passwordValidator(String password)
	{
		 String regx ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()�[{}]:;',?/*~$^+=<>]).{8,20}$";
		    Pattern pattern = Pattern.compile(regx);
		    Matcher matcher = pattern.matcher(password);
		  return matcher.find();
	}

	@GetMapping("/all")
	public List<userTweets> getAllTweets()
	{
		return this.tweetService.getAllTweets();
	}
	
	@GetMapping("/{userName}")
	public List<userTweets> getAllMyTweets(@PathVariable("userName")String userName)
	{
		return this.tweetService.getAllMyTweets(userName);
	}
	
	@RequestMapping(value = "/{userName}/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean postTweet(@RequestBody userTweets tweet)
	{
		if(this.tweetService.registerTweet(tweet)==1)
		{
			return true;
		}
		return false;
			
	}
	
	@RequestMapping(value = "/{userName}/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean postTweet(@RequestBody userTweets tweet, @PathVariable("id")String tweetId)
	{
		if(this.tweetService.updateTweet(tweet, tweetId)==1)
		{
			return true;
		}
		return false;
			
	}
	
	@RequestMapping(value = "/{userName}/like/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean LikeTweet( @PathVariable("userName")String userName, @PathVariable("id")String tweetId)
	{
		if(this.tweetService.likeTweet(userName, tweetId)==1)
		{
			return true;
		}
		return false;
			
	}
	@RequestMapping(value = "/{userName}/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean postTweet(@PathVariable("id")String tweetId)
	{
		if(this.tweetService.deleteTweet(tweetId)==1)
		{
			return true;
		}
		return false;
			
	}
}
