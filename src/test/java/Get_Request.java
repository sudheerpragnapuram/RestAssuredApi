import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Get_Request {

	@Test	
	void getRequests()
	{
//		//specify base URI
//		RestAssured.baseURI="https://reqres.in/";
//
//		//Request object
//		RequestSpecification httprequest = RestAssured.given();
//
//		//Response object
//		Response response = httprequest.request(Method.GET,"/api/users?page=2");
//
//
//		String responsebody = response.getBody().asString();
//		System.out.println("ResponseBody is "+responsebody);
//
//		//Status code
//		int statusCode = response.getStatusCode();
//		System.out.println("Satus code is "+ statusCode);
//		Assert.assertEquals(statusCode, 200);
//
//		//Status line verification
//		//		String statusLine = response.getStatusLine();
//		//		Assert.assertEquals(statusLine, "");
//	
//		// Success code validation
////		String successcode =  response.jsonPath().get(" Successcode");
////		Assert.assertEquals(successcode,"Operation_Scuccess");
//
//		
////	//TO verify Headers, Caputre details of headers from Response
////		
////		String contentType = response.header("Content-Type");
////		System.out.println("Content type is "+ contentType);
////		
////		Assert.assertEquals(contentType,"");
//		
//		//Printing A
//	}
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"")
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		
		
	}

}
