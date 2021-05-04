package com.tweetapp.service;

import java.util.List;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.model.Users;
import com.tweetapp.model.userTweets;
import com.tweetapp.util.TweetAppEncryption;

public class TweetService {
	
	public List<Users> getAllUsers()
	{
		List<Users> allUsers=null;
		TweetDao tweetDao=new TweetDao();
	    allUsers=tweetDao.getData();
	    return allUsers;
	}
	public Users loginUser(String loginId, String password)
	{
		List<Users> allUsers=getAllUsers();
		TweetAppEncryption decryption=new TweetAppEncryption();
		for (Users users : allUsers) {
			if(users.getLoginId().equals(loginId) && users.getPassword().equals(password))
			{
				return users;
			}
		}
		return null;
	}
	public int registerUser(Users user)
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.registerUser(user);
	}
	public List<userTweets> getAllTweets()
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.getAllTweets();
	}
	public List<userTweets> getAllMyTweets(String userName)
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.getAllMyTweets(userName);
	}
	public int registerTweet(userTweets tweet)
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.registerTweet(tweet);
	}
	public int updateTweet(userTweets tweet, String tweetId)
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.updateTweet(tweet, tweetId);
	}
	public int deleteTweet(String tweetId)
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.deleteTweet(tweetId);
	}
	public int likeTweet(String userName, String tweetId)
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.likeTweet(userName, tweetId);
	}
	public int updatePassword(String newpassword,String email)
	{
		TweetDao tweetDao=new TweetDao();
		return tweetDao.updatePassword(newpassword, email);
	}
	public String resetPasswordStatus(String password,String userEmail) {
		String status="";
		boolean pointer=false;
		List<Users> allUsers=getAllUsers();
		for (Users users : allUsers) {
			if(users.getEmail().equals(userEmail))
			{
				pointer=true;
			}
		}
		if(pointer==false)
		{
			status="User not existed";
		}
		else
		{
			updatePassword(password, userEmail);
			status="Password reset successfully";
		}
		return status;
	}
}
