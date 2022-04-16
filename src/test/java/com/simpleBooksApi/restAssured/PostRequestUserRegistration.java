package com.simpleBooksApi.restAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestUserRegistration {

	@Before
	public void before() {
		RestAssured.baseURI ="https://knowingtrade.backendless.app/api/users";
	}
	
	@Test
	public void testUserRegistrationPostRequest() {
		
		//Create a request object
		RequestSpecification request = RestAssured.given();
		
		String email = "kp4@gmail.com";
		
		//Add header
		request.header("Content-Type","application/json");
		
		//Add the request body 
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("email", email);
		jsonObject.put("password", "password");
		
		request.body(jsonObject);
		
		Response response=request.post("/register");
		
		Assert.assertEquals(200,response.statusCode());
		
		JsonPath path = response.jsonPath();
		
		String respEmail = path.getString("email");
		
		System.out.println("respEmail : "+respEmail);
		
		Assert.assertEquals(respEmail, email);
		
	}
}
