package com.simpleBooksApi.restAssured;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class CallingApisUsingChainedMethod {
	
	public String baseUrl="https://simple-books-api.glitch.me";
	
	
	 @Before
	    public void beforeMethod(){
	        RestAssured.baseURI = baseUrl;
	    }
	 
	 
	@Test
	public void getListOfBooks() {
		//MethodChaining		
		RestAssured.given().when().get("/books").then().statusCode(200);
	}

}
