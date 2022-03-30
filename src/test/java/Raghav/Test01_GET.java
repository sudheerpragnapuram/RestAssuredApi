package Raghav;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test01_GET {

	@Test
	void test_01(){


		Response response = get("https://reqres.in/api/users?page=2");

		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("Content-Type"));
		System.out.println(response.getTime());


//		String responsebody = response.getBody().asString();
//		System.out.println("ResponseBody is "+responsebody);
//
//		Status code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);


	}
	
	@Test
	void test_02(){
		
		given().get().then().statusCode(200);
	}
	
	
}
