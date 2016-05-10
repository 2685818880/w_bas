package com.java.Exception;

@SuppressWarnings("serial")
public class FormatAgeException extends Exception{
    
    String message;
	public FormatAgeException(){}
	public FormatAgeException(String  message){
		message=this.message+" error";
	}
	public String toString(){
		return message;
	}

}
