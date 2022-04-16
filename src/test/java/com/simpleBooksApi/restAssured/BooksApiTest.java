package com.simpleBooksApi.restAssured;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class BooksApiTest {

	
	@Before
	public void beforeTest() {
		RestAssured.baseURI = "https://simple-books-api.glitch.me";
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testListOFBooks() {
		
		//Prepare Request or create a request object - using given() method
		RequestSpecification request = RestAssured.given();
		
		//Query params or path parmas or request body
		
		// calling the api using the HTTP Method 
		Response response = request.get("/books");
		
	
		//	System.out.println(response.asPrettyString());
		
		//Test the APIs
		
		//Test status code 
		int responseCode =  response.getStatusCode();
		
		Assert.assertEquals(200, responseCode);
		
		JsonPath jsonPath = response.jsonPath();
		
		//Get the id of first book from response
		
		int id = jsonPath.getInt("id[0]");
		
		System.out.println("id of first book : "+id);
		
		String bookName = jsonPath.get("name[0]");
		
		boolean isAvailable = jsonPath.getBoolean("available[1]");
		
		System.out.println(bookName + " is available "+isAvailable);
		
	}
	
}
