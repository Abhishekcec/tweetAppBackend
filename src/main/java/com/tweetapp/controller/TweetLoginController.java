package com.tweetapp.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.Users;
import com.tweetapp.service.TweetService;

@RestController
@CrossOrigin
@RequestMapping({"/tweets"})
public class TweetLoginController {



	TweetService tweetService=new TweetService();
	
	@GetMapping(path = {"/login"})
	public Users isUserExist(@RequestHeader String userName, @RequestHeader("password")String password)
	{
			Users user=this.tweetService.loginUser(userName, password);
			return user;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean registerUser(@RequestBody Users newUser)
	{
		int res=this.tweetService.registerUser(newUser);
		if(res==1)
		{
			return true;
		}
		return false;
	}
	public boolean updateUserPassword(String newPassword,String user)
	{
		if(this.tweetService.updatePassword(newPassword, user)==1)
		{
			return true;
		}
		return false;
	}
	
	@GetMapping("/users/all")
	public List<Users> getAllUsers()
	{
		return this.tweetService.getAllUsers();
	}
	
	@GetMapping(path= {"/{loginId}/forgot"})
	public String resetStatus(@RequestHeader("password")String password, @PathVariable("loginId")String email) {
		return this.tweetService.resetPasswordStatus(password, email);
	}
}
