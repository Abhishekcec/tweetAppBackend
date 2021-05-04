package com.tweetapp.model;

public class userTweets {

	@Override
	public String toString() {
		return "--:)  " + userName + " tweeted as =" + tweetMsg + "...!";
	}
	private String userName;
	private String tweetId;
	private String tweetMsg;
	private String tweetTag;
	public userTweets(String tweetId, String userName, String tweetMsg, String tweetTag) {
		super();
		this.tweetId = tweetId;
		this.userName = userName;
		this.tweetMsg = tweetMsg;
		this.tweetTag = tweetTag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTweetMsg() {
		return tweetMsg;
	}
	public void setTweetMsg(String tweetMsg) {
		this.tweetMsg = tweetMsg;
	}
	public userTweets() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getTweetTag() {
		return tweetTag;
	}
	public void setTweetTag(String tweetTag) {
		this.tweetTag = tweetTag;
	}
}
