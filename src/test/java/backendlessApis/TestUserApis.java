package backendlessApis;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TestUserApis {

	 String applicationId = "10B6B2A1-F3A1-7CBD-FFA6-8EC43F365300";
	    String cloudApiKey="5AF5AAFA-FA9C-4710-9549-E791C534CA4F";

	    String baseUrl = "https://knowingtrade.backendless.app";
	    
	    String objectId = "";
	    
	    RequestSpecification request;
	    Response response;
	    
	@BeforeMethod
	public void beforeTest() {
		
		RestAssured.baseURI = baseUrl;
		request = RestAssured.given();
	}
	
	@Test(priority=1)
	public void testLoginAPI() {
		
		
		
		JSONObject object = new JSONObject();
		object.put("login","kp@gmail.com");
		object.put("password", "password");
		
		request.body(object);
		
		response =  request.post("/api/users/login");
		
		System.out.println(response.asPrettyString());
		
		Assert.assertEquals(response.statusCode(), 200);
		
		JsonPath path = response.jsonPath();
		
		objectId = path.getString("objectId");
		
	}
	
	@Test
	public void testLoginwithInvalidCredentials() {
		
		JSONObject object = new JSONObject();
		object.put("login","kp@gmail.com");
		object.put("password", "password1");
		
		request.body(object);
		
		response =  request.post("/api/users/login");
		
		System.out.println(response.asPrettyString());
		
		Assert.assertEquals(response.statusCode(), 401);
		
		JsonPath path = response.jsonPath();
		
		Assert.assertEquals(path.getInt("code"),3003);
	}
	
	@Test(priority = 2)
	public void testEnableDisableUser() {
		
		JSONObject object = new JSONObject();
		object.put("userStatus","ENABLED");
		
		request.body(object);
		
		response = request.put("https://api.backendless.com/"+applicationId+"/"+cloudApiKey+"/users/"+objectId+"/status");
		System.out.println(response.asPrettyString());
		
		
		Assert.assertEquals(response.statusCode(), 200);
	}
}
