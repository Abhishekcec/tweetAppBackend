package com.tweetapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.tweetapp.controller.TweetOperationalController;

public class TweetAppValidation {
	public static Scanner sc=new Scanner(System.in);
	public static TweetOperationalController operationalController=new TweetOperationalController();
	public static Object validationChecker(String type) {
		boolean validated=false;
		String name=null;
		while (!validated)
			{
				try
				{	
					name =sc.nextLine();
					if(type=="name") {
						validated=operationalController.nameValidator(name);
						if(!validated)
							throw new TweetAppCustomException("Please Enter Valid Name");
						
					}
						if(type=="gender") {
							validated=operationalController.genderValidator(name);
							if(!validated)
								throw new TweetAppCustomException("Please Enter Gender From Options");
						
						}
						if(type=="date")
						{
							 java.util.Date sqlDate=new SimpleDateFormat("dd/MM/yyyy").parse(name); 
							 return sqlDate;
						}
						
						if(type=="email") {
							validated=operationalController.emailValidator(name);
							if(!validated)
								throw new TweetAppCustomException("Please Enter Valid Email Id");
						}
						if(type=="password") {
							validated=operationalController.passwordValidator(name);
							if(!validated)
								throw new TweetAppCustomException("Please Enter Strong Password.\nThe password must contain upperCase,lowerCase,specialCharacters with length between 8-20 ");
						}
						
						if(type=="userPassword") {
							validated=operationalController.passwordValidator(name);
							if(!validated)
								throw new TweetAppCustomException("Please Enter Valid Password ");
						}
				}
				catch ( InputMismatchException ime)
				{
					System.out.println("Incorrect input, Please Enter a valid input.");
				}
				catch(ParseException ime)
				{
					System.out.println("Incorrect input, Please Enter the date in the given format.");
				} catch (TweetAppCustomException e) {
					
				}
			}
		return name;
	}
}
