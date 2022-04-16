package com.simpleBooksApi.restAssured;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import responsePojo.Book;

public class ParseResponseUsingPojo {
		
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
			
			//Pojo 
			
			List<Book> books = jsonPath.getList("$",Book.class);	
			
			System.out.println(books.toString());
			
			for(Book book : books) {
				
				System.out.println(book.getName() + "  is available "  +book.isAvailable());
			}
		
		}
		
	

}
